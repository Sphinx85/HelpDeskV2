package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.DTO.UserDTO;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService{

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepository.save(userConverter.fromDTOtoUser(userDTO));
        return userConverter.fromUserToDTO(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::fromUserToDTO)
                .collect(Collectors.toList());
    }
}
