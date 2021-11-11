package ua.hubanov.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.hubanov.application.entity.persons.Developer;
import ua.hubanov.application.service.MainService;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/getSuitableDevForProject")
    public ResponseEntity<List<Developer>> getSuitableDevelopersForProject(@RequestParam Long projectId) {
        return new ResponseEntity<>(mainService.getSuitableDevelopersForProject(projectId), HttpStatus.OK);
    }
}
