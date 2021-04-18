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

import de.hsheilbronn.EgypttoursAServer.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 * @see <https://docs.spring.io/spring-security/site/docs/5.4.5/reference/html5/>
 */

@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomUserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
       try {
        if(!userDetails.isAccountNonExpired()){
            throw new BadCredentialsException("Account is expired");
        }
        if(!userDetails.isAccountNonLocked()){
            throw new BadCredentialsException("Account is locked");
        }
        if(!userDetails.isCredentialsNonExpired()){
            throw new BadCredentialsException("Credentials expired");
        }
        if(!userDetails.isEnabled()){
            throw new BadCredentialsException("Account is disabled");
        }
        if (passwordEncoder.matches(authentication.getCredentials().toString(),userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
        }
       }catch (NullPointerException e) {
           throw new BadCredentialsException("Code:W25SE57F");
       }
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
