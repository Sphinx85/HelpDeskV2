package ru.brightway.HelpDeskV2.services;

import ru.brightway.HelpDeskV2.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    void deleteUser(Integer userId);
    List<UserDTO> findAll();
}
