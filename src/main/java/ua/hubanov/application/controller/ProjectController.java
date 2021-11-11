package ua.hubanov.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.application.dto.ProjectDTO;
import ua.hubanov.application.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectDTO newProject) {
        return new ResponseEntity<>(projectService.save(newProject), HttpStatus.CREATED);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable(value = "id") Long projectId,
                                                    @Valid @RequestBody ProjectDTO projectDetails) {
        return new ResponseEntity<>(projectService.update(projectId, projectDetails), HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable long id) {
        projectService.delete(id);
    }

    @GetMapping("/projects/addSkill")
    public ResponseEntity<ProjectDTO> addSkillToProject(@RequestParam Long projectId,
                                                     @RequestParam Long skillId, @RequestParam int level) {
        return new ResponseEntity<>(projectService.addSkillToProject(projectId, skillId, level), HttpStatus.OK);
    }
}
