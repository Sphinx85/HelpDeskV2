package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.Entites.QuickMessages;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Контроллер для работы пользователя. Основной функционал программы.
 */

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

    @Autowired
    private StatusBuilder statusBuilder;

    @Autowired
    private ModelService modelService;

    @Autowired
    private Classifier classifier;

    @Autowired
    private QuickService quickService;

    /**
     * Метод отображения главной страницы приложения
     * @param principal Входной параметр принимает текущего пользователя
     * @param model Входной параметр передает в модель текущего пользователя
     * @return Возвращает основную страницу приложения current.html
     */
    @GetMapping("/messages/current")
    public String currentMessages(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        List<Message> messages = user.getMessages();
        messages.removeIf(message -> !message.getActual());
        user.setMessages(messages);
        List<QuickMessages> quickMessages = quickService.findAll();
        model.addAttribute(user);
        model.addAttribute("quickMessages", quickMessages);
        return "current" ;
    }

    /**
     * Метод отображения деталей заявки. Карточка заявки.
     * Не используется с версии 0.4.2
     * @param id Входной параметро принимает на вход из строки id заявки
     * @param model Входной параметр вкладывает в модель текущую заявку
     * @return Возвращает страницу
     */
    @GetMapping("/details/{id}")
    public String messageDetails(@PathVariable("id") Integer id, Model model, Principal principal){
        Optional<Message> messageResponse = messageService.findById(id);
        Message message = messageResponse.get();
        model.addAttribute(message);
        modelService.inject(principal,model);
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
        if (description.length() < 7) return "redirect:/workplace/messages/current";
        Message message = new Message();
        message.setDescription(description);
        message.setUser(userService.findByUsername(principal.getName()));
        message.setType(typeService.findById(classifier.getTypeId(description)).get());
        message.setPriority(priorityService.findById(classifier.getPriorityId(description)).get());
        message.setSupport_id(0);
        message.setStatus(statusBuilder.construct(message));
        message.setActual(true);
        message.setDate(new Date());
        if (spam(message, principal)){
            return "redirect:/workplace/messages/current";
        }
        messageService.saveMessage(message);
        return "redirect:/workplace/messages/current";
    }

    private boolean spam(Message message, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Message> controlMessages = user.getMessages();
        for (Message control: controlMessages){
            if (control.getActual()
            && message.getDescription().equals(control.getDescription())
            &&(message.getDate().getTime()-control.getDate().getTime()<900000))
                return true;
            if (control.getActual()
                    &&(message.getDate().getTime()-control.getDate().getTime()<180000))
                return true;
        }
        return false;
    }
}
