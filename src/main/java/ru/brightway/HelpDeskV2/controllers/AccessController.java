package ru.brightway.HelpDeskV2.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.Access;
import ru.brightway.HelpDeskV2.services.interfaces.AccessService;

import java.util.List;

@RestController
@RequestMapping("/access")
@AllArgsConstructor
@Log
@CrossOrigin
public class AccessController {

    private final AccessService accessService;

    @PostMapping("/save")
    public Access saveAccess(@RequestBody Access access){
        log.info("Save access: " + access);
        return accessService.saveAccess(access);
    }

    @GetMapping("/findAll")
    public List<Access> findAllAccess(){
        log.info("Find all access");
        return accessService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccess(@PathVariable Integer id){
        log.info("Access " + id + " deleted");
        accessService.deleteAccess(id);
        return ResponseEntity.ok().build();
    }
}
