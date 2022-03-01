package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.Type;

import java.util.List;

public interface TypeService {
    Type saveType (Type type);
    void deleteType(Integer typeId);
    List<Type> findAll();
}
