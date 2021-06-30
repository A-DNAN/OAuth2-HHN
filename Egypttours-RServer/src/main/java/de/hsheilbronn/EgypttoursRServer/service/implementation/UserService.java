package de.hsheilbronn.EgypttoursRServer.service.implementation;

import de.hsheilbronn.EgypttoursRServer.dao.UEmailRepository;
import de.hsheilbronn.EgypttoursRServer.dao.UPhoneRepository;
import de.hsheilbronn.EgypttoursRServer.dao.UProfileRepository;
import de.hsheilbronn.EgypttoursRServer.dto.UserDTO;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.mapper.UserMapper;
import de.hsheilbronn.EgypttoursRServer.model.user.UEmail;
import de.hsheilbronn.EgypttoursRServer.model.user.UPhone;
import de.hsheilbronn.EgypttoursRServer.model.user.UProfile;
import de.hsheilbronn.EgypttoursRServer.model.user.User;
import de.hsheilbronn.EgypttoursRServer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service("UserService")
public class UserService implements IUserService {

   @Autowired
   UProfileRepository uProfileRepository;
   @Autowired
   UEmailRepository uEmailRepository;
   @Autowired
   UPhoneRepository uPhoneRepository;
   @Autowired
   UserMapper UserMapper;



    @Override
    public UserDTO findUserProfileByUsername(String username) throws NotFoundException {
        User user = new User();
        int value = 0;
        user.setUsername(username);
        Optional<UProfile> uProfile = uProfileRepository.findByUser(user);
        Optional<UEmail> uEmail = uEmailRepository.findByUser(user);
        Optional<UPhone> uPhone = uPhoneRepository.findByUser(user);
        UserDTO userDTO = new UserDTO();
            if(uProfile.isPresent()) {
                userDTO = UserMapper.toDTO(uProfile.get());
               ++value;
            }
            if(uEmail.isPresent()){
                userDTO.setEmail(uEmail.get().getEmail());
               ++value;}
            if(uPhone.isPresent()){
                userDTO.setPhone_number(uPhone.get().getPhone());
                ++value;
            }
           if(value > 0)
           return userDTO;

       throw new NotFoundException();
    }
    public void updateUserProfileByUsername(UserDTO userDTO) {
        int value = 0;
        User user = new User();
        user.setUsername(userDTO.getUsername());

        UProfile uProfile;

        Optional<UProfile> uProfile_p = uProfileRepository.findByUser(user);
        Optional<UEmail> uEmail_p = uEmailRepository.findByUser(user);
        Optional<UPhone> uPhone_p = uPhoneRepository.findByUser(user);

        if(uProfile_p.isPresent())
            uProfile = uProfile_p.get();
        else {
            uProfile = new UProfile();
            uProfile.setUser(user);
        }

        if(userDTO.getFirstname() != null && !userDTO.getFirstname().isEmpty()){
        uProfile.setFirstname(userDTO.getFirstname());
        ++value;
        }
        if(userDTO.getLastname() != null && !userDTO.getLastname().isEmpty()){
        uProfile.setLastname(userDTO.getLastname());
            ++value;
        }
        if(userDTO.getMiddle_name() != null && !userDTO.getMiddle_name().isEmpty()){
        uProfile.setMiddle_name(userDTO.getMiddle_name());
            ++value;
        }
        if(userDTO.getPictureUrl() != null && !userDTO.getPictureUrl().isEmpty()){
        uProfile.setPictureUrl(userDTO.getPictureUrl());
            ++value;}
        if(userDTO.getGender() != null && userDTO.getGender().matches("^Female$|^Male$")){
        uProfile.setGender(UProfile.Gender.valueOf(userDTO.getGender()));
            ++value;
        }
        if(userDTO.getBirthdate() != null && userDTO.getBirthdate().matches("^([1][0-9][0-9][0-9]|[2][0][0-9][0-9])\\-([0][1-9]|[1][0-2])\\-([0-2][0-9]|[3][0-1])$")){
        uProfile.setBirthdate(LocalDate.parse(userDTO.getBirthdate()));
            ++value;
        }
        if(value > 0)
            uProfileRepository.save(uProfile);

            if(uEmail_p.isPresent() && userDTO.getEmail() != null && !userDTO.getEmail().isEmpty())
                uEmailRepository.delete(uEmail_p.get());
            if(userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
                UEmail uEmail = new UEmail();
                uEmail.setUser(user);
                uEmail.setEmail(userDTO.getEmail());
                uEmailRepository.save(uEmail);
            }
            if(uPhone_p.isPresent() && userDTO.getPhone_number() != null && !userDTO.getPhone_number().isEmpty())
                    uPhoneRepository.delete(uPhone_p.get());
            if(userDTO.getPhone_number() != null && !userDTO.getPhone_number().isEmpty()) {
                    UPhone uPhone = new UPhone();
                    uPhone.setUser(user);
                    uPhone.setPhone(userDTO.getPhone_number());
                    uPhoneRepository.save(uPhone);
            }
        }

    public UEmail findUserEmailByUsername(String username) throws NotFoundException{

        return null;
    }
    public UPhone findUserPhoneByUsername(String username) throws NotFoundException{
        return null;
    }



    public String map(User user){
       Optional<UProfile> uProfile = uProfileRepository.findByUser(user);
       if(uProfile.isPresent())
           return uProfile.get().getPictureUrl();
        return "";
    }

    public User map(String value){
        return null;
    }

}
