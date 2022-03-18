package ru.brightway.HelpDeskV2.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.services.interfaces.*;

@Controller
@RequestMapping("/admin/delete")
@Data
@AllArgsConstructor
@CrossOrigin
public class DeleteController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccessService accessService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/user/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id){
        userService.deleteUser(id);
        return "redirect:/admin/allUsers";
    }

    @GetMapping("/role/{id}")
    public String deleteRole(@PathVariable(name = "id") Integer id){
        accessService.deleteAccess(id);
        return "redirect:/admin/allRoles";
    }

    @GetMapping("/message/{id}")
    public String deleteMessage(@PathVariable(name = "id") Integer id){
        messageService.deleteMessage(id);
        return "redirect:/admin/allMessages";
    }

    @GetMapping("/priority/{id}")
    public String deletePriority(@PathVariable(name = "id") Integer id){
        priorityService.deletePriority(id);
        return "redirect:/admin/allPriorities";
    }

    @GetMapping("/type/{id}")
    public String deleteType(@PathVariable(name = "id") Integer id){
        typeService.deleteType(id);
        return "redirect:/admin/allTypes";
    }
}
