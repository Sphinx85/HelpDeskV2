package ru.brightway.HelpDeskV2.services.interfaces;

public interface Classifier {
    int getTypeId(String message);
    int getPriorityId(String message);
    void addKeyWords(String message, Integer alert, Integer id);
}
