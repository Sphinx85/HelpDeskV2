package ru.brightway.HelpDeskV2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.QuickMessages;
import ru.brightway.HelpDeskV2.repository.QuickRepository;
import ru.brightway.HelpDeskV2.services.interfaces.QuickService;

import java.util.ArrayList;
import java.util.List;
@Service
public class DefaultQuickService implements QuickService {

    @Autowired
    private QuickRepository quickRepository;

    @Override
    public void saveQuickMessages(QuickMessages messages) {
        quickRepository.save(messages);
    }

    @Override
    public void deleteMessage(Integer messageId) {
        quickRepository.deleteById(messageId);
    }

    @Override
    public List<QuickMessages> findAll() {
        return new ArrayList<>(quickRepository.findAll());
    }
}
