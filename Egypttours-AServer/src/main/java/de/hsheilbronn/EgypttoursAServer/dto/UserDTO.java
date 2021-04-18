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

package de.hsheilbronn.EgypttoursAServer.dto;

import java.io.Serializable;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

public class UserDTO implements Serializable {
    private String username;
    private String password;
    private String email;
    private String password_confirmation;
    // redirection url after registration if not empty
    private String return_to;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }


    public String return_to() {
        return return_to;
    }
}