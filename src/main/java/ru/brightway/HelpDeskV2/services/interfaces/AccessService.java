package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.Role;

import java.util.List;
import java.util.Optional;

public interface AccessService {
    void saveAccess (Role role);
    void deleteAccess(Integer accessId);
    List<Role> findAll();
    Optional<Role> findById(int role);
}