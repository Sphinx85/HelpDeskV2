package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.repository.MessageRepository;
import ru.brightway.HelpDeskV2.services.interfaces.MessageService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultMessageService implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Integer messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public List<Message> findAll() {
        return new ArrayList<>(messageRepository.findAll());
    }




}
