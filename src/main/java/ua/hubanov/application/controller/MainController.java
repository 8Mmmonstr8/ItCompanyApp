package ua.hubanov.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.hubanov.application.dto.DeveloperDTO;
import ua.hubanov.application.facade.DeveloperFacade;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    DeveloperFacade developerFacade;

    @GetMapping("/getSuitableDevForProject")
    public ResponseEntity<List<DeveloperDTO>> getSuitableDevelopersForProject(@RequestParam Long projectId) {
        return new ResponseEntity<>(developerFacade.getSuitableDevelopersForProject(projectId), HttpStatus.OK);
    }
}
