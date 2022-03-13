package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.security.Principal;

@Controller
@Data
@RequestMapping("/workplace")
public class WorkPlaceController {

    @Autowired
    private UserService userService;



    @GetMapping("/messages/current")
    public String currentMessages(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute(user);
        return "current";
    }
}
