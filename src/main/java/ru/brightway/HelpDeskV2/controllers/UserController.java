package ru.brightway.HelpDeskV2.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.DTO.UserDTO;
import ru.brightway.HelpDeskV2.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        log.info("Save user: " + userDTO);
        return userService.saveUser(userDTO);
    }

    @GetMapping("/findAll")
    public List<UserDTO> findAllUsers() {
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
