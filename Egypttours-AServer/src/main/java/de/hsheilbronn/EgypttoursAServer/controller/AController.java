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

package de.hsheilbronn.EgypttoursAServer.controller;

import de.hsheilbronn.EgypttoursAServer.dto.UserDTO;
import de.hsheilbronn.EgypttoursAServer.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Controller()
public class AController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(
            @RequestBody(required = true) UserDTO userDTO
    ){
        if (userDTO.getUsername() == null
// V               || userDTO.getUsername().isBlank()
                || userDTO.getUsername().isEmpty()){
            return ResponseEntity.badRequest().body("missing_username_value");
        }
// V       if (customUserDetailsService.userExists(userDTO.getUsername().strip().toLowerCase())){
        if (customUserDetailsService.userExists(userDTO.getUsername().replaceAll("\\s+", "").toLowerCase())){
            return ResponseEntity.badRequest().body("existing_username");
        }
        if (userDTO.getEmail() == null
//  V              || userDTO.getEmail().isBlank()
                || userDTO.getEmail().isEmpty()){
            return ResponseEntity.badRequest().body("missing_email_value");
        }
// V       if (customUserDetailsService.emailExists(userDTO.getEmail().strip().toLowerCase())){
        if (customUserDetailsService.emailExists(userDTO.getEmail().replaceAll("\\s+", "").toLowerCase())){
            return ResponseEntity.badRequest().body("existing_email");
        }
        if (userDTO.getPassword() == null
// V               || userDTO.getPassword().isBlank()
                || userDTO.getPassword().isEmpty()){
            return ResponseEntity.badRequest().body("missing_password_value");
        }
        if (userDTO.getPassword_confirmation() == null
// V               || userDTO.getPassword_confirmation().isBlank()
                || userDTO.getPassword_confirmation().isEmpty()){
            return ResponseEntity.badRequest().body("missing_password_confirmation_value");
        }
        if (!userDTO.getPassword().equals(userDTO.getPassword_confirmation())){
            return ResponseEntity.badRequest().body("password_mismatch");
        }
            customUserDetailsService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
