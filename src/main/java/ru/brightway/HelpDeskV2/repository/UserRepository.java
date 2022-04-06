package ru.brightway.HelpDeskV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brightway.HelpDeskV2.Entites.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
