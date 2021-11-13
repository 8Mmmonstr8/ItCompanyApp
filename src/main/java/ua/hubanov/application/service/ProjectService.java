package ua.hubanov.application.service;

import ua.hubanov.application.entity.projects.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();
    Project getProjectById(Long id);
    Project save(Project newProject);
    Project update(Long id, Project projectDetails);

    void delete(Long id);

    Project addSkillToProject(Long projectId, Long skillId, int level);
}
