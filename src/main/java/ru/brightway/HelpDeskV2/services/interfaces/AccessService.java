package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.Access;

import java.util.List;

public interface AccessService {
    Access saveAccess (Access access);
    void deleteAccess(Integer accessId);
    List<Access> findAll();
}
