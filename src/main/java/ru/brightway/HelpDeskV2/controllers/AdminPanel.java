package ru.brightway.HelpDeskV2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminPanel {

    @GetMapping("/panel")
    public String adminPanel(){
        return "adminPanel";
    }
}
