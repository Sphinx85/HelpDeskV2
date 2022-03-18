package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.*;
import ru.brightway.HelpDeskV2.services.interfaces.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
@Data
@RequestMapping("/admin")
@CrossOrigin
public class AdminPanel {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private AccessService accessService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/panel")
    public String adminPanel(){
        return "adminPanel";
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "allLists";
    }

    @GetMapping("/allMessages")
    public String allMessages(Model model){
        model.addAttribute("messages", messageService.findAll());
        return "allLists";
    }

    @GetMapping("/allTypes")
    public String allTypes(Model model){
        model.addAttribute("types", typeService.findAll());
        return "allLists";
    }

    @GetMapping("/allPriorities")
    public String allPriorities(Model model){
        model.addAttribute("priorities", priorityService.findAll());
        return "allLists";
    }

    @GetMapping("/allRoles")
    public String allRoles(Model model){
        model.addAttribute("roles", accessService.findAll());
        return "allLists";
    }

    @GetMapping("/form/{typeModel}")
    public String form(@PathVariable(name = "typeModel") String typeModel,Model model){
        switch (typeModel){
            case "user":
                model.addAttribute("user",new User());

            case "type":
                model.addAttribute("type",new Type());

            case "priority":
                model.addAttribute(new Priority());

            case "role":
                model.addAttribute(new Role());
        }
        return "form";
    }

    @PostMapping("/newUser")
    public String newUser(@ModelAttribute(name = "first_name") String first_name,
                          @ModelAttribute(name = "last_name") String last_name,
                          @ModelAttribute(name = "username") String username,
                          @ModelAttribute(name = "password") String password,
                          @ModelAttribute(name = "role") int role){
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(accessService.findById(role).get());
        User user = new User();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin/allUsers";
    }

    @PostMapping("/newType")
    public String newType(@ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Type type = new Type();
        type.setId(id);
        type.setDescription(description);
        typeService.saveType(type);
        return "redirect:/admin/allTypes";
    }

    @PostMapping("/newPriority")
    public String newPriority(@ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Priority priority = new Priority();
        priority.setId(id);
        priority.setDescription(description);
        priorityService.savePriority(priority);
        return "redirect:/admin/allLists";
    }

    @PostMapping("/newRole")
    public String newRole(@ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Role role = new Role();
        role.setId(id);
        role.setRole(description);
        accessService.saveAccess(role);
        return "redirect:/admin/allLists";
    }

    @PutMapping("upUser/{id}")
    public String upUser(@PathVariable("id") int id,
                          @ModelAttribute(name = "first_name") String first_name,
                          @ModelAttribute(name = "last_name") String last_name,
                          @ModelAttribute(name = "username") String username,
                          @ModelAttribute(name = "password") String password,
                          @ModelAttribute(name = "role") int role){
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(accessService.findById(role).get());
        User user = userService.findById(id).get();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin/allLists";
    }

    @PutMapping("/upType/{id}")
    public String upType(@PathVariable("id") int idType,
                          @ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Type type = typeService.findById(idType).get();
        type.setId(id);
        type.setDescription(description);
        typeService.saveType(type);
        return "redirect:/admin/allLists";
    }

    @PutMapping("/upPriority/{id}")
    public String upPriority(@PathVariable("id") int idPriority,
                              @ModelAttribute(name = "id") int id,
                              @ModelAttribute(name = "description") String description){
        Priority priority = priorityService.findById(idPriority).get();
        priority.setId(id);
        priority.setDescription(description);
        priorityService.savePriority(priority);
        return "redirect:/admin/allLists";
    }

    @PutMapping("/upRole/{id}")
    public String upRole(@PathVariable("id") int idRole,
                         @ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Role role = accessService.findById(idRole).get();
        role.setId(id);
        role.setRole(description);
        accessService.saveAccess(role);
        return "redirect:/admin/allLists";
    }

}
