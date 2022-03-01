package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.repository.UserRepository;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userRepository.findAll());

    }
}