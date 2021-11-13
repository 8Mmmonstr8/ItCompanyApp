package ua.hubanov.application.facade;

import ua.hubanov.application.dto.ProjectDTO;

import java.util.List;

public interface ProjectFacade {

    List<ProjectDTO> getAllProjects();
    ProjectDTO getProjectById(Long id);
    ProjectDTO save(ProjectDTO newProject);
    ProjectDTO update(Long id, ProjectDTO projectDetails);

    void delete(Long id);

    ProjectDTO addSkillToProject(Long projectId, Long skillId, int level);
}
