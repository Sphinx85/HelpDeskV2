package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.KeyWords;

import java.util.HashSet;
import java.util.List;

public interface KeyWordsService {
    void save(KeyWords keyWords);
    List<KeyWords> findAll();
}
