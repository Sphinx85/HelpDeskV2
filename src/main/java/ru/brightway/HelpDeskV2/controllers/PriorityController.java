package ru.brightway.HelpDeskV2.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.Priority;
import ru.brightway.HelpDeskV2.services.interfaces.PriorityService;

import java.util.List;

@RestController
@RequestMapping("/priority")
@AllArgsConstructor
@Log
@CrossOrigin
public class PriorityController {

    private final PriorityService priorityService;

    @PostMapping("/save")
    public Priority savePriority(@RequestBody Priority priority){
        log.info("Save priority");
        return priorityService.savePriority(priority);
    }

    @GetMapping("/all")
    public List<Priority> findAllPriority(){
        log.info("Find all priority");
        return priorityService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePriority(@PathVariable Integer id){
        log.info("Priority deleted");
        priorityService.deletePriority(id);
        return ResponseEntity.ok().build();
    }
}
