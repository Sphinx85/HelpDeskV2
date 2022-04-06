package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.services.interfaces.StatusBuilder;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

/**
 * Сервис генерации статусов заявок для отображения пользователю.
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
@AllArgsConstructor
public class StatusForMessage implements StatusBuilder {

    @Autowired
    UserService userService;

    /**
     * Метод для отображения статуса заявки пользователю.
     * @param message На вход метод принимает объект Message, для построения статуса на основе
     *                идентификатора ответственного специалиста
     * @return Возвращает строку. Готовый результат сборки статуса
     */
    @Override
    public String construct(Message message) {
        StringBuilder status_complete = new StringBuilder();
        if (message.getSupport_id() == 0)
            return (status_complete.append("Вашу заявку еще не приняли в работу").toString());
        return (status_complete.append("Вашей заявкой занимается: ")
                .append(userService.findById(message.getSupport_id()).get().getFirst_name())
                .append(" ")
                .append(userService.findById(message.getSupport_id()).get().getLast_name())
                .append(".").toString());
    }
}
