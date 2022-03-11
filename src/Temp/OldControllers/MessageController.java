package ru.brightway.HelpDeskV2.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.Message;
import ru.brightway.HelpDeskV2.services.interfaces.MessageService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
@Log
@CrossOrigin
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/save")
    public Message saveMessage(@RequestBody Message message){
        log.info("Save message");
        return messageService.saveMessage(message);
    }

    @GetMapping("/all")
    public List<Message> findAllMessages(Principal principal){
        log.info("View all messages");
        return messageService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Integer id){
        log.info("Message deleted: " + id);
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }
}
