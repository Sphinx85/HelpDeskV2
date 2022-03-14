package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.services.interfaces.MessageService;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.util.Optional;

//@RestController
//@Data
//@RequestMapping("/workplace")
//@CrossOrigin
public class WorkplaceRestController {

    private Integer idMessage;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @PostMapping("/details/{id}")
    public void messageId(@PathVariable Integer id){
        idMessage = id;
    }

    @GetMapping("/details/{id}")
    public Optional<Message> messageDetails(@PathVariable("id") Integer id){

        return messageService.findById(id);
    }
}
