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

package de.hsheilbronn.EgypttoursAServer.service;

import de.hsheilbronn.EgypttoursAServer.dao.UEmailRespository;
import de.hsheilbronn.EgypttoursAServer.dao.UserRepository;
import de.hsheilbronn.EgypttoursAServer.dto.UserDTO;
import de.hsheilbronn.EgypttoursAServer.model.UEmail;
import de.hsheilbronn.EgypttoursAServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

   @Autowired
   UserRepository userRepository;
   @Autowired
   UEmailRespository uEmailRespository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findById(s);
        optionalUser.orElseThrow( () -> new UsernameNotFoundException("Bad Credentials"));
        return optionalUser.get();
    }

    public void createUser(UserDTO userDTO) {
        User user = new User();
//V        UEmail email = new UEmail(userDTO.getEmail().strip().toLowerCase(),user);
//V        user.setUsername(userDTO.getUsername().strip().toLowerCase());
        UEmail email = new UEmail(userDTO.getEmail().replaceAll("\\s+", "").toLowerCase(),user);
        user.setUsername(userDTO.getUsername().replaceAll("\\s+", "").toLowerCase());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        uEmailRespository.save(email);
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }
    public boolean emailExists(String email) {
        return uEmailRespository.existsById(email);
    }

}
