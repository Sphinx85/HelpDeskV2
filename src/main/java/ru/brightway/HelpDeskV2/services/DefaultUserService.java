package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Role;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.repository.AccessRepository;
import ru.brightway.HelpDeskV2.repository.UserRepository;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис работы с пользователями
 */
@Service
@Data
@AllArgsConstructor
public class DefaultUserService implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Метод сохранения пользователя
     * @param user Принимает пользователя
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Метод удаления пользователя. Помечает неактуальным в базе.
     * @param userId Принимает ID пользователя
     */
    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Метод поиска всех пользователей
     * @return Возвращает коллекцию пользователей
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(userRepository.findAll());

    }

    /**
     * Метод поиска по username. Типовая реализация.
     * @param nickname Принимает логин пользователя
     * @return Возвращает пользователя
     */
    @Override
    public User findByUsername(String nickname) {
        return userRepository.findByUsername(nickname);
    }

    /**
     * Метод обновления информации о пользователе в базе
     * @param user Принимает пользователя
     */
    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    /**
     * Метод поиска пользователя по ID
     * @param id Принимает ID пользователя
     * @return Возвращает пользователя
     */
    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    /**
     * Метод загрузки пользователя по username. Используется Spring Security для userDetails в авторизации.
     * @param username Принимает логин пользователя
     * @return Возвращает пользователя и все сопоставленные ему роли
     * @throws UsernameNotFoundException Пробрасывает ошибку, если пользователь не найден в базе
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null || !user.getEnable()){
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /**
     * Метод преобразования списка ролей пользователя в специальный параметр ролей для security.
     * @param roles Принимает коллекцию ролей
     * @return Возвращает коллекцию ролей
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
