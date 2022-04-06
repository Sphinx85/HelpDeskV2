package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.repository.MessageRepository;
import ru.brightway.HelpDeskV2.services.interfaces.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис работы с заявками
 */
@Service
@AllArgsConstructor
public class DefaultMessageService implements MessageService {
    @Autowired
    private final MessageRepository messageRepository;

    /**
     * Метод сохранения заявки в базе
     * @param message Принимает заявку
     */
    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    /**
     * Метод удаления заявки. Помечает ее не актуальной.
     * @param messageId Принимает ID заявки
     */
    @Override
    public void deleteMessage(Integer messageId) {
        messageRepository.deleteById(messageId);
    }

    /**
     * Метод поиска всех заявок в базе
     * @return Возвращает коллекцию заявок
     */
    @Override
    public List<Message> findAll() {
        return new ArrayList<>(messageRepository.findAll());
    }

    /**
     * Метод поиска заявки по ID
     * @param id Принимает ID заявки
     * @return Возвращает заявку
     */
    @Override
    public Optional<Message> findById(Integer id) {
        return messageRepository.findById(id);
    }

    /**
     * Метод обновления информации в заявке
     * @param message Принимает заявку
     */
    @Override
    public void update(Message message) {
        messageRepository.save(message);
    }
}
