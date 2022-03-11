package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.Role;

import java.util.List;

public interface AccessService {
    Role saveAccess (Role role);
    void deleteAccess(Integer accessId);
    List<Role> findAll();
}
