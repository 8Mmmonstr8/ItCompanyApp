package ua.hubanov.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.application.dto.ProjectDTO;
import ua.hubanov.application.facade.ProjectFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectFacade projectFacade;

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return new ResponseEntity<>(projectFacade.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        return new ResponseEntity<>(projectFacade.getProjectById(id), HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectDTO newProject) {
        return new ResponseEntity<>(projectFacade.save(newProject), HttpStatus.CREATED);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable(value = "id") Long projectId,
                                                    @Valid @RequestBody ProjectDTO projectDetails) {
        return new ResponseEntity<>(projectFacade.update(projectId, projectDetails), HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable long id) {
        projectFacade.delete(id);
    }

    @GetMapping("/projects/addSkill")
    public ResponseEntity<ProjectDTO> addSkillToProject(@RequestParam Long projectId,
                                                     @RequestParam Long skillId, @RequestParam int level) {
        return new ResponseEntity<>(projectFacade.addSkillToProject(projectId, skillId, level), HttpStatus.OK);
    }
}
