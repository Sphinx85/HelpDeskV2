package ru.brightway.HelpDeskV2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    @GetMapping("/")
    public String homaPage(){
        return "home";
    }

//    @GetMapping("/messages/all")
//    public String pageForAuthenticatedUsers(Principal principal){
//        return "messages" + principal.getName();
//    }
}
