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

package de.hsheilbronn.EgypttoursAServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionTrackingMode;
import java.util.EnumSet;


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@SpringBootApplication
public class EgypttoursAServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EgypttoursAServerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EgypttoursAServerApplication.class);
	}

//	@Override
//	public void contextInitialized(ServletContextEvent event) {
//		ServletContext context = event.getServletContext();
//		EnumSet<SessionTrackingMode> modes =
//				EnumSet.of(SessionTrackingMode.SSL);
//		context.setSessionTrackingModes(modes);
//	}
}
