package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.util.List;
import java.util.Optional;

@Controller

@Data
@RequestMapping("/workplace")
@CrossOrigin
public class WorkPlaceController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private PriorityService priorityService;

    @GetMapping("/messages/current")
    public String currentMessages(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute(user);
        return "current" ;
    }

//    @PostMapping("/details/{id}")
//    public void messageId(@PathVariable Integer id){
//        idMessage = id;
//    }
//

    @GetMapping("/details/{id}")
    public String messageDetails(@PathVariable("id") Integer id, Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        Optional<Message> messageResponse = messageService.findById(id);
        Message message = messageResponse.get();
        model.addAttribute(message);
        return "details";
    }

    @PostMapping("/message/save")
    public String quickMessage(@ModelAttribute(name = "description") String description, Principal principal){
        Message message = new Message();
        message.setDescription(description);
        message.setUser(userService.findByUsername(principal.getName()));
        message.setType(typeService.findById(1).get());
        message.setPriority(priorityService.findById(1).get());
        messageService.saveMessage(message);
        return "redirect:/workplace/messages/current";
    }
}
