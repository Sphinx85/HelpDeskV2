package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.MessageService;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@Data
@RequestMapping("/workplace")
public class WorkPlacePanel {

    private MessageService messageService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages/all")
    public List<Message> allMessages(){
        return messageService.findAll();
    }

    @GetMapping("/messages/current")
    public List<Message> currentMessages(Principal principal){
        User user = userService.findByUsername(principal.getName());
        return user.getMessages();
    }
}
