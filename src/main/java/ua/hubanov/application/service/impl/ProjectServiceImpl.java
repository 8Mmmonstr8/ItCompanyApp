package ua.hubanov.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SkillRepository skillRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectException("No project with id: " + id));
    }

    @Override
    public Project save(Project newProject) {
        return projectRepository.save(newProject);
    }

    @Override
    public Project update(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectException("No project with id: " + id));
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setDurationInMonth(projectDetails.getDurationInMonth());
        return projectRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Project addSkillToProject(Long projectId, Long skillId, int level) {
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
        return projectRepository.save(project);
    }
}
