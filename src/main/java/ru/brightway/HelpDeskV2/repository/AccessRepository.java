package ru.brightway.HelpDeskV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brightway.HelpDeskV2.Entites.Role;

public interface AccessRepository extends JpaRepository<Role, Integer> {
}