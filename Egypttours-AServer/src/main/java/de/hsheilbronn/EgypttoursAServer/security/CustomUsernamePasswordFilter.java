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

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 * @see <https://docs.spring.io/spring-security/site/docs/5.4.5/reference/html5/>
 */

// to serve only Oauth2 requests
@Component
public class CustomUsernamePasswordFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return (
                request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST") != null &&
                request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST").toString().matches(
               ".*\\/oauth2\\/authorize\\?.*"
                 ))
                ||(request.getRequestURI() !=null && request.getRequestURI().equals("/TGEgypt-as-api/join") && request.getMethod().equals(HttpMethod.POST.toString()));
       }


}
