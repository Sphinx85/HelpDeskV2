package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.Message;

import java.util.List;

public interface MessageService {
    Message saveMessage (Message message);
    void deleteMessage(Integer messageId);
    List<Message> findAll();



}
