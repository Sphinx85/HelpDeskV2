package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.KeyWords;
import ru.brightway.HelpDeskV2.repository.KeyWordsRepository;
import ru.brightway.HelpDeskV2.services.interfaces.KeyWordsService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultKeyWordsService implements KeyWordsService {

    @Autowired
    private KeyWordsRepository keyWordsRepository;

    public void save(KeyWords keyWords){
        keyWordsRepository.save(keyWords);
    }

    public List<KeyWords> findAll(){
        return new ArrayList<>(keyWordsRepository.findAll());
    }
}
