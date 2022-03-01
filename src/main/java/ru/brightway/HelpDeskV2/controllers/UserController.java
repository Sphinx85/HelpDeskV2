package ru.brightway.HelpDeskV2.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        log.info("Save user: " + user);
        return userService.saveUser(user);
    }

    @GetMapping("/findAll")
    public List<User> findAllUsers() {
        log.info("Find all users");
        return userService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        log.info("Delete user: " + id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}