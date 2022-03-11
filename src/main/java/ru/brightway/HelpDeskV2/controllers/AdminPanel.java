package ru.brightway.HelpDeskV2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/workplace/admin")
public class AdminPanel {

    @GetMapping("/panel")
    public String adminPanel(Principal principal){
        return "panel" + principal.getName();
    }
}
