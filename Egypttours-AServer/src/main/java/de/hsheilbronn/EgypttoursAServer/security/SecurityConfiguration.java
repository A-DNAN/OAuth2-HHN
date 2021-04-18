/**
 *  Copyright (C) 2021  the original author or authors.
 *
 * 		This program is free software: you can redistribute it and/or modify
 * 		it under the terms of the GNU General Public License as published by
 * 		the Free Software Foundation, either version 3 of the License, or
 * 		(at your option) any later version.
 *
 * 		This program is distributed in the hope that it will be useful,
 * 		but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 		GNU General Public License for more details.
 *
 * 		You should have received a copy of the GNU General Public License
 * 		along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.hsheilbronn.EgypttoursAServer.security;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.util.Base64;
import java.util.UUID;
import de.hsheilbronn.EgypttoursAServer.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.util.ResourceUtils;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 * @see <https://docs.spring.io/spring-security/site/docs/5.4.5/reference/html5/>
 */

@Configuration(proxyBeanMethods = false)
@Import(OAuth2AuthorizationServerConfiguration.class)
@EnableWebSecurity
//		(debug = true)
public class SecurityConfiguration {

//	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	CustomUsernamePasswordAuthenticationProvider authenticationProvider;
	@Autowired
	CustomUsernamePasswordFilter customUsernamePasswordFilter;
	@Autowired
	CustomSessionAuthenticationStrategy customSessionAuthenticationStrategy;

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		    http.authenticationProvider(authenticationProvider)
				.addFilterBefore(customUsernamePasswordFilter, SecurityContextPersistenceFilter.class)
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
				.mvcMatchers("/assets/**").permitAll()
								.mvcMatchers( "/error").permitAll()
								.mvcMatchers( "/join").permitAll()
								.mvcMatchers( "/TGEgypt-as-api/join").permitAll()
								.anyRequest().authenticated()
				).formLogin().loginPage("/login").permitAll()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.sessionAuthenticationStrategy(customSessionAuthenticationStrategy).and().cors()
					// to be modified
					.and().csrf().ignoringAntMatchers("/TGEgypt-as-api/join", "/join");
//			.maximumSessions(1).expiredSessionStrategy(customSessionInformationExpiredStrategy)
//		    .and().logout().logoutUrl("/logout")
//			.and().csrf().disable();
		return http.build();
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:8081");
//			}
//		};
//	}


//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer(){
//		return (web -> web
//				.ignoring().antMatchers(""));
//	}

	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder (){
		return new BCryptPasswordEncoder();
	}


	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("client")
				.clientSecret("secret")
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
// back to FE Client
				.redirectUri("https://seserver.se.hs-heilbronn.de:9443/TGEgypt/authorized")
				.redirectUri("http://localhost:8081/authorized")
				.scope(OidcScopes.OPENID)
//				.scope(OidcScopes.PROFILE)
//				.scope(OidcScopes.EMAIL)
				.scope("read")
				.scope("write")
				.tokenSettings(tokenSettings ->
				tokenSettings.accessTokenTimeToLive(Duration.ofMinutes(40))
				.refreshTokenTimeToLive(Duration.ofMinutes(70))
				)
				.clientSettings(clientSettings ->
						clientSettings.requireUserConsent(false))
				.build();
		return new InMemoryRegisteredClientRepository(registeredClient);
	}



	@Bean
	public JWKSource<SecurityContext> jwkSource()  {
		RSAKey rsaKey = generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}

// Auto RSAkeys generation
//  public static RSAKey generateRsa() {
//	try {
//		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//		keyPairGenerator.initialize(4096, new SecureRandom());
//		KeyPair keyPair = keyPairGenerator.generateKeyPair();
//		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//
//	return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
//	} catch (NoSuchAlgorithmException ex) {
//		throw new IllegalStateException(ex);
//	}
//}


	public static RSAKey generateRsa() {
		try {
			String piKeyS = new String(Files.readAllBytes(Paths.get(ResourceUtils.getFile("classpath:keys/Pikey.pem").toURI())));
			String puKeyS = new String(Files.readAllBytes(Paths.get(ResourceUtils.getFile("classpath:keys/Pukey.pem").toURI())));
			piKeyS = piKeyS.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
			puKeyS = puKeyS.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");;

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(piKeyS));
			PrivateKey piKey = keyFactory.generatePrivate(keySpecPKCS8);
			X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(puKeyS));
			RSAPublicKey puKey = (RSAPublicKey) keyFactory.generatePublic(keySpecX509);

			return new RSAKey.Builder(puKey)
					.privateKey(piKey)
					.keyID("38 60 F4 E7 5A E2 DF AD 75 D0 FE 20 C4 17 96 0A 37 BE 62 3B").build();
		}catch (Exception ex){
			return null;
		}
	}


	@Bean
	public ProviderSettings providerSettings() {
//		ProviderSettings providerSettings = new ProviderSettings();
//		providerSettings.authorizationEndpoint("/v2/authorize");
		return new ProviderSettings().issuer("https://seserver.se.hs-heilbronn.de:9443/TGEgypt-as-api");
	}





}
