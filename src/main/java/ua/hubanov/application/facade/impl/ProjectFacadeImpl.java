package ua.hubanov.application.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.hubanov.application.dto.ProjectDTO;
import ua.hubanov.application.entity.projects.Project;
import ua.hubanov.application.facade.ProjectFacade;
import ua.hubanov.application.service.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectFacadeImpl implements ProjectFacade {

    @Autowired
    ProjectService projectService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects().stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        return modelMapper.map(projectService.getProjectById(id), ProjectDTO.class);
    }

    @Override
    public ProjectDTO save(ProjectDTO newProject) {
        Project project = projectService.save(modelMapper.map(newProject, Project.class));
        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public ProjectDTO update(Long id, ProjectDTO projectDetails) {
        Project project = modelMapper.map(projectDetails, Project.class);
        return modelMapper.map(projectService.update(id, project), ProjectDTO.class);
    }

    @Override
    public void delete(Long id) {
        projectService.delete(id);
    }

    @Override
    public ProjectDTO addSkillToProject(Long projectId, Long skillId, int level) {
        return modelMapper.map(projectService.addSkillToProject(projectId, skillId, level), ProjectDTO.class);
    }
}
