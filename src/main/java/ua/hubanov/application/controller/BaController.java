package ua.hubanov.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hubanov.application.entity.persons.Ba;
import ua.hubanov.application.repository.BaRepository;

import java.util.List;

@RestController
public class BaController {

    @Autowired
    BaRepository baRepository;

    @GetMapping("/persons/bas")
    public ResponseEntity<List<Ba>> getAllBas() {
        return new ResponseEntity<>(baRepository.findAll(), HttpStatus.OK);
    }
}
