package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser (User user);
    void deleteUser(Integer userId);
    List<User> findAll();
    User findByUsername(String nickname);
    void update(User user);
    Optional<User> findById(int id);
}
