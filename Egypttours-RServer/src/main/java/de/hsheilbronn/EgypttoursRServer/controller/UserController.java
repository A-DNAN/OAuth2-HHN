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

package de.hsheilbronn.EgypttoursRServer.controller;

import de.hsheilbronn.EgypttoursRServer.dto.UserDTO;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.service.IUserService;
import de.hsheilbronn.EgypttoursRServer.service.implementation.AngebotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@RestController()
//@CrossOrigin(allowedHeaders = "*", origins = "http://localhost:8081", methods = {RequestMethod.POST,
//RequestMethod.GET,RequestMethod.OPTIONS, RequestMethod.DELETE,RequestMethod.PATCH})
@CrossOrigin(origins = {"https://seserver.se.hs-heilbronn.de:9443","http://localhost:8081"})
public class UserController {

    @Autowired
    @Qualifier("UserService")
    IUserService userService;

    @CrossOrigin(origins = {"*"})
    @GetMapping("/ik-auth")
    public Map<String, String> IKAuth(){
        return AngebotService.getAuthenticatedParams(null,0,"private_Vc8NOKDFqQ3W8oeU0fXN9LJ9Cpo=");
    }

    @PutMapping("/user")
    public ResponseEntity<String> userProfileUpdate(@RequestBody UserDTO userDTO, Authentication authentication){
       userDTO.setUsername(authentication.getName());
        userService.updateUserProfileByUsername(userDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<Object> userProfile(Authentication authentication){
        try {
            return ResponseEntity.ok(userService.findUserProfileByUsername(authentication.getName()));
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    //  @PostMapping
    //  removeUser

    //  @PostMapping
    //  updateUser

    //  @PostMapping
    //  changePassword

    // @GetMapping("/userinfo")
    // userinfo Endpoint

//     @CrossOrigin(origins = "http://localhost:8081")
//     @PostMapping("/isvalid")
//     public Boolean verifyToken () {
//         return true;
//     }

}
