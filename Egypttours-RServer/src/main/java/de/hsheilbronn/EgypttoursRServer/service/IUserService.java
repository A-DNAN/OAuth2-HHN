package de.hsheilbronn.EgypttoursRServer.service;

import de.hsheilbronn.EgypttoursRServer.dto.UserDTO;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;

public interface IUserService {


    public UserDTO findUserProfileByUsername(String username) throws NotFoundException;
    public void updateUserProfileByUsername(UserDTO userDTO);


}
