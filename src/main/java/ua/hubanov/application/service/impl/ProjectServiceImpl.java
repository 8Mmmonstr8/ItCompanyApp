package ua.hubanov.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.application.dto.ProjectDTO;
import ua.hubanov.application.entity.enums.Level;
import ua.hubanov.application.entity.projects.Project;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.exception.ProjectException;
import ua.hubanov.application.exception.SkillException;
import ua.hubanov.application.repository.ProjectRepository;
import ua.hubanov.application.repository.SkillRepository;
import ua.hubanov.application.service.ProjectService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectException("No project with id: " + id));
        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public ProjectDTO save(ProjectDTO newProject) {
        Project project = projectRepository.save(modelMapper.map(newProject, Project.class));
        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public ProjectDTO update(Long id, ProjectDTO projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectException("No project with id: " + id));
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setDurationInMonth(projectDetails.getDurationInMonth());
        Project updatedProject = projectRepository.save(project);
        return modelMapper.map(updatedProject, ProjectDTO.class);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProjectDTO addSkillToProject(Long projectId, Long skillId, int level) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException("No project with id: " + projectId));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new SkillException("No skill with id " + skillId));

        project.getSkills().stream()
                .filter(projectSkill -> projectSkill.getSkill().equals(skill))
                .findAny()
                .ifPresent(projectSkill -> {
                    throw new SkillException(projectSkill.getSkill().getName() + " already exists in this project");
                });

        project.addSkill(skill, Level.of(level));
        skillRepository.save(skill);
        Project updatedProject = projectRepository.save(project);
        return modelMapper.map(updatedProject, ProjectDTO.class);
    }
}
