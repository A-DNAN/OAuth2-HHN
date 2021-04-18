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

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomCORSFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getHeader(HttpHeaders.ORIGIN) !=null &&
                ( request.getHeader(HttpHeaders.ORIGIN).equals("https://seserver.se.hs-heilbronn.de:9443")
         ||  request.getHeader(HttpHeaders.ORIGIN).equals("http://localhost:8081")
        )

        ) {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader(HttpHeaders.ORIGIN));
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "*");
        }
            if (request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString())) {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        else {
            filterChain.doFilter(request, response);
        }
    }


}
