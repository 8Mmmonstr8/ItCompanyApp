package ua.hubanov.application.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.hubanov.application.AbstractProjectTest;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.entity.projects.Project;
import ua.hubanov.application.exception.ProjectException;
import ua.hubanov.application.exception.SkillException;
import ua.hubanov.application.repository.ProjectRepository;
import ua.hubanov.application.repository.SkillRepository;
import ua.hubanov.application.service.impl.ProjectServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest extends AbstractProjectTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void getAllProjectsTest() {
        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> testList = projectService.getAllProjects();

        assertEquals(5, testList.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void getProjectByIdTest() {
        long id = 3L;
        Project project = projects.stream().filter(x -> x.getId() == id).findAny()
                .orElseThrow(() -> new ProjectException("There is no project with id " + id));

        when(projectRepository.findById(id)).thenReturn(Optional.ofNullable(project));

        Project testProject = projectService.getProjectById(id);

        assertEquals("DHL", testProject.getName());
        verify(projectRepository, times(1)).findById(id);
    }

    @Test(expected = ProjectException.class)
    public void getProductByIdThrowsExceptionTest() {
        long id = 6L;
        when(projectRepository.findById(id)).thenReturn(Optional.empty());
        projectService.getProjectById(id);
    }

    @Test
    public void addSkillToProjectTest() {
        Project project = projects.get(0);
        Skill skill = new Skill("Java");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        when(projectRepository.save(project)).thenReturn(project);
        when(skillRepository.save(skill)).thenReturn(skill);

        projectService.addSkillToProject(1L, 1L, 3);

        assertTrue(project.getSkills().stream().anyMatch(x -> x.getSkill().equals(skill)));
    }

    @Test(expected = SkillException.class)
    public void addSkillToProjectThrowsSkillException() {
        Project project = projects.get(0);
        Skill skill = new Skill("Java");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        when(projectRepository.save(project)).thenReturn(project);
        when(skillRepository.save(skill)).thenReturn(skill);

        projectService.addSkillToProject(1L, 1L, 3);
        projectService.addSkillToProject(1L, 1L, 4);
    }
}
