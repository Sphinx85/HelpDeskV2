package ru.brightway.HelpDeskV2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.DefaultUserService;

import javax.validation.Valid;

//@Controller
public class RegistrationController {

//    @Autowired
//    private DefaultUserService userService;
//
//    @GetMapping("/registration")
//    public String registration(Model model){
//        model.addAttribute("userForm" , new User());
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) return "registration";
//        userService.saveUser(userForm);
//        return "redirect:/";
//    }
}
