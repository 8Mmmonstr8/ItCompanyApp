package ua.hubanov.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.application.dto.DeveloperDTO;
import ua.hubanov.application.facade.DeveloperFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DeveloperController {

    @Autowired
    DeveloperFacade developerFacade;

    @GetMapping("/persons/developers")
    public ResponseEntity<List<DeveloperDTO>> getAllDevelopers() {
        return new ResponseEntity<>(developerFacade.getAllDevelopers(), HttpStatus.OK);
    }

    @GetMapping("/persons/developers/{id}")
    public ResponseEntity<DeveloperDTO> getDeveloperById(@PathVariable Long id) {
        return new ResponseEntity<>(developerFacade.getDeveloperById(id), HttpStatus.OK);
    }

    @PostMapping("/persons/developers")
    public ResponseEntity<DeveloperDTO> create(@Valid @RequestBody DeveloperDTO newDeveloper) {
            return new ResponseEntity<>(developerFacade.save(newDeveloper), HttpStatus.CREATED);
    }

    @PutMapping("/persons/developers/{id}")
    public ResponseEntity<DeveloperDTO> updateDeveloper(@PathVariable(value = "id") Long developerId,
                                                     @Valid @RequestBody DeveloperDTO developerDetails) {
        return new ResponseEntity<>(developerFacade.update(developerId, developerDetails), HttpStatus.OK);
    }

    @DeleteMapping("/persons/developers/{id}")
    public void deleteProject(@PathVariable long id) {
        developerFacade.delete(id);
    }

    @GetMapping("/persons/developers/addSkill")
    public ResponseEntity<DeveloperDTO> addSkillToDeveloper(@RequestParam Long developerId,
                                                     @RequestParam Long skillId, @RequestParam int level) {
        return new ResponseEntity<>(developerFacade.addSkillToDeveloper(developerId, skillId, level), HttpStatus.OK);
    }
}
