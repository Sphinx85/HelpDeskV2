package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.MessageService;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.security.Principal;
import java.util.List;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Controller
@Data
@RequestMapping("/support")
@CrossOrigin
public class SupportPanel {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @GetMapping("/panel")
    public String supportPanel(){return "supportPanel";}

    /**
     * Метод отрисовки списка всех заявок.
     * @param model Входным параметром является модель, в которую помещается коллекция заявок из БД
     * @return Возвращает страницу allLists.html
     */
    @GetMapping("/allMessages")
    public String allMessages(Model model){
        List<Message> freeMessages = messageService.findAll();
        freeMessages.removeIf(message -> message.getSupport_id() != 0);
        model.addAttribute("messages", freeMessages);
        return "allLists";
    }

    /**
     * Метод отображения списка заявок текущего специалиста поддержки
     * @param principal Входной параметр принимает текущего пользователя
     * @param model Входной параметр передает в модель список заявок текущего специалиста поддержки
     * @return Возвращает основную страницу приложения current.html
     */
    @GetMapping("/current")
    public String currentMessages(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("currentMessages",user.getMessages());
        return "allLists" ;
    }

    /**
     * Метод присвоения заявки определенному сотруднику поддержки.
     * Вызывается POST запросом по нажатию на кнопку "Принять заявку" специалистом поддержки
     * @param message_id Входной параметр принимает id заявки
     * @param principal Входной параметр принимает текущего пользователя
     *                  для присвоения заявке параметра support_id
     * @return Возвращает переадресацию на страницу текущих заявок специалиста
     */
    @PostMapping("/apply/{id}")
    public String applyMessage(@RequestParam(name = "id") Integer message_id, Principal principal){
        Message message = messageService.findById(message_id).get();
        message.setSupport_id(userService.findByUsername(principal.getName()).getId());
        return "redirect:/support/current";
    }

    /**
     * Метод конвертирует заявку в статус не актуальной в результате ее закрытия
     * и отправке POST запроса по нажатию кнопки "Завершить"
     * @param message_id Входной параметр принимает в запросе id заявки
     * @return Возвращает переадресацию на страницу текущих заявок специалиста
     */
    @PostMapping("/complete/{id}")
    public String completeMessage(@RequestParam(name = "id") Integer message_id){
        Message message = messageService.findById(message_id).get();
        message.setActual(false);
        return "redirect:/support/current";
    }
}