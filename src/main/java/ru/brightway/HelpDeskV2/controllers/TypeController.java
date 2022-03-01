package ru.brightway.HelpDeskV2.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.Type;
import ru.brightway.HelpDeskV2.services.interfaces.TypeService;

import java.util.List;

@RestController
@RequestMapping("/type")
@AllArgsConstructor
@Log
@CrossOrigin
public class TypeController {

    private final TypeService typeService;

    @PostMapping("/save")
    public Type saveType(@RequestBody Type type){
        log.info("Save type");
        return typeService.saveType(type);
    }

    @GetMapping("/all")
    public List<Type> findAllType(){
        log.info("Find all type");
        return typeService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Integer id){
        log.info("Type deleted");
        typeService.deleteType(id);
        return ResponseEntity.ok().build();
    }
}
