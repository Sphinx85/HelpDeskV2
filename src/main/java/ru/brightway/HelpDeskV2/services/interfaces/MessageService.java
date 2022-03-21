package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    void saveMessage (Message message);
    void deleteMessage(Integer messageId);
    List<Message> findAll();
    Optional<Message> findById(Integer id);
}