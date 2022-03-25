package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.Message;

public interface StatusBuilder {
    String construct(Message message);
}
