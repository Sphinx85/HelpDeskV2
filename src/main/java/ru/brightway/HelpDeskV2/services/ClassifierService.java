package ru.brightway.HelpDeskV2.services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.KeyWords;
import ru.brightway.HelpDeskV2.services.interfaces.Classifier;
import ru.brightway.HelpDeskV2.services.interfaces.KeyWordsService;
import ru.brightway.HelpDeskV2.services.interfaces.StemmerPorter;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
public class ClassifierService implements Classifier {

    @Autowired
    private KeyWordsService keywords;

    @Autowired
    private StemmerPorter stemmer;

    @Override
    public int getTypeId(String message) {
        String[] keys = message.split("\\s");
        List<KeyWords> keyWordsList = keywords.findAll();
        Map<Integer, Integer> hashIds = new HashMap<>();
        List<Integer> idIndex = new ArrayList<>();

        for (String key: keys){
            if (key.length()>3){
                String stem = stemmer.stem(key);
                for (KeyWords keyWords : keyWordsList){
                    if (keyWords.getKeyword().equals(stem)) {
                        if (hashIds.get(keyWords.getTypeId()) != null){
                            idIndex.add(keyWords.getTypeId());
                            hashIds.computeIfPresent(keyWords.getTypeId(), (k, v) -> v + 1);
                        } else hashIds.put(keyWords.getTypeId(),1);
                    }
                }
            }
        }

        int targetId = 0;
        Collection<Integer> values = hashIds.values();
        if (!hashIds.isEmpty())
            for (int value: values)
                if (value>targetId) targetId = value;

        for (int id: idIndex){
            if (hashIds.get(id).equals(targetId)) return id; //получаю количество заявок, а не айди.
        }

        return 0;
    }

    @Override
    public int getPriorityId(String message) {
        String[] keys = message.split("\\s");
        List<KeyWords> keyWordsList = keywords.findAll();
        List<Integer> idIndex = new ArrayList<>();
        for (String key : keys) {
            if (key.length() > 3) {
                String stem = stemmer.stem(key);
                for (KeyWords keyWords : keyWordsList) {
                    if (keyWords.getKeyword().equals(stem)) idIndex.add(keyWords.getPriorityId());
                }
            }
        }

        int sum = 0;
        for (Integer index : idIndex) {
            sum += index;
        }
        if (idIndex.size() == 0){
            return 1;
        }
        return sum/idIndex.size();
    }

    @Override
    public void addKeyWords(String message, Integer alert, Integer id) {
        String[] keyword = message.split("\\s");
        List<KeyWords> keyWordList = keywords.findAll();
        List<String> keywordArray = keyWordList.stream().map(KeyWords::getKeyword).collect(Collectors.toList());
        for (String key : keyword) {
            if (key.length() > 3) {
                String stem = stemmer.stem(key);
                if (keywordArray.contains(stem)) {
                    continue;

                }
                KeyWords keyWord = new KeyWords(stem,alert,id);
                keywords.save(keyWord);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!");
            }
        }
    }
}
