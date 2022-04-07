package ru.brightway.HelpDeskV2.services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.ModelService;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.security.Principal;

/**
 * Сервис добавления модели пользователя в контекст страницы
 */
@Service
@Data
public class ModelInjection implements ModelService {

    @Autowired
    private UserService userService;

    /**
     * Метод для добавления пользователя в модель
     * @param principal Принимает текущего пользователя из контекста Security
     * @param model Принимает модель для контекста Thymeleaf
     * @return Возвращает модель с пользователем.
     */
    @Override
    public Model inject(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute(user);
        return model;
    }
}
