package ua.hubanov.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.application.dto.SkillDTO;
import ua.hubanov.application.facade.SkillFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SkillController {

    @Autowired
    SkillFacade skillFacade;

    @GetMapping("/skills")
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        return new ResponseEntity<>(skillFacade.getAllSkills(), HttpStatus.OK);
    }

    @GetMapping("/skills/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Long id) {
        return new ResponseEntity<>(skillFacade.getSkillById(id), HttpStatus.OK);
    }

    @PostMapping("/skills")
    public ResponseEntity<SkillDTO> create(@Valid @RequestBody SkillDTO newSkill) {
        return new ResponseEntity<>(skillFacade.save(newSkill), HttpStatus.CREATED);
    }

    @PutMapping("/skills/{id}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable(value = "id") Long skillId,
                                                @RequestBody SkillDTO skillDetails) {
        return new ResponseEntity<>(skillFacade.update(skillId, skillDetails), HttpStatus.OK);
    }

    @DeleteMapping("/skills/{id}")
    public void deleteSkill(@PathVariable long id) {
        skillFacade.delete(id);
    }
}
