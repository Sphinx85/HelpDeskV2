package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.QuickMessages;

import java.util.List;

public interface QuickService {
    void saveQuickMessages(QuickMessages messages);
    void deleteMessage(Integer messageId);
    List<QuickMessages> findAll();
}
