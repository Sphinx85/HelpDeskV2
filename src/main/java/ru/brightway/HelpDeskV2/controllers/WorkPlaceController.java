package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.MessageService;
import ru.brightway.HelpDeskV2.services.interfaces.PriorityService;
import ru.brightway.HelpDeskV2.services.interfaces.TypeService;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.security.Principal;
import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Controller
@Data
@RequestMapping("/workplace")
@CrossOrigin
public class WorkPlaceController {
    //Блок инъекций необходимых сервисов
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private PriorityService priorityService;

    /**
     * Метод отображения главной страницы приложения
     * @param principal Входной параметр принимает текущего пользователя
     * @param model Входной параметр передает в модель текущего пользователя
     * @return Возвращает основную страницу приложения current.html
     */
    @GetMapping("/messages/current")
    public String currentMessages(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute(user);
        return "current" ;
    }

    /**
     * Метод отображения деталей заявки. Карточка заявки.
     * @param id Входной параметро принимает на вход из строки id заявки
     * @param model Входной параметр вкладывает в модель текущую заявку
     * @return Возвращает страницу details.html
     */
    @GetMapping("/details/{id}")
    public String messageDetails(@PathVariable("id") Integer id, Model model){
        Optional<Message> messageResponse = messageService.findById(id);
        Message message = messageResponse.get();
        model.addAttribute(message);
        return "details";
    }

    /**
     * Метод сохранения новой заявки. Работает как для быстрых, так и собственных заявок.
     * @param description Входной параметр принимает сообщение
     * @param principal Входной параметр принимает текущего пользователя
     * @return Возвращает переадресацию на основную страницу программы
     */
    @PostMapping("/message/save")
    public String quickMessage(@ModelAttribute(name = "description") String description, Principal principal){
        Message message = new Message();
        message.setDescription(description);
        message.setUser(userService.findByUsername(principal.getName()));
        message.setType(typeService.findById(1).get());
        message.setPriority(priorityService.findById(1).get());
        message.setSupport_id(0);
        message.setStatus(construct(message));
        message.setActual(true);
        messageService.saveMessage(message);
        return "redirect:/workplace/messages/current";
    }

    /**
     * Метод для отображения статуса заявки пользователю.
     * @param message На вход метод принимает объект Message, для построения статуса на основе
     *                идентификатора ответственного специалиста
     * @return Возвращает строку. Готовый результат сборки статуса
     */
    private String construct(Message message){
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