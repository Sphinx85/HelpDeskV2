package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.User;

import java.util.List;

public interface UserService {
    User saveUser (User user);
    void deleteUser(Integer userId);
    List<User> findAll();
}