package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.MessageService;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@Data
public class ReportController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @GetMapping("/admin/report")
    public String adminReport(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Message> messages = messageService.findAll();
        List<User> users = userService.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "report";
    }

    @GetMapping("/support/report")
    public String supportReport(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Message> messages = messageService.findAll();
        List<User> users = userService.findAll();
        messages.removeIf(message -> !(message.getSupport_id().equals(user.getId())));
        model.addAttribute("messages", messages);
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "report";
    }

    @GetMapping("/user/report")
    public String userReport(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Message> messages = user.getMessages();
        List<User> users = userService.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "report";
    }
}
