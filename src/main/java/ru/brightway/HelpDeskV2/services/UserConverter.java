package ru.brightway.HelpDeskV2.services;

import org.springframework.stereotype.Component;
import ru.brightway.HelpDeskV2.DTO.UserDTO;
import ru.brightway.HelpDeskV2.Entites.User;

@Component
public class UserConverter {

    public User fromDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirst_name());
        user.setLastName(userDTO.getLast_name());
        user.setAccessId(userDTO.getAccess_id());
        return user;
    }


    public UserDTO fromUserToDTO(User savedUser) {
        return UserDTO.builder()
                .id(savedUser.getId())
                .first_name(savedUser.getFirstName())
                .last_name(savedUser.getLastName())
                .access_id(savedUser.getAccessId())
                .build();
    }
}
