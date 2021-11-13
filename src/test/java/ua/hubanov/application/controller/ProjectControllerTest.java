package ua.hubanov.application.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.hubanov.application.AbstractProjectTest;
import ua.hubanov.application.dto.ProjectDTO;
import ua.hubanov.application.facade.impl.ProjectFacadeImpl;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest extends AbstractProjectTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectFacadeImpl projectFacade;

    @MockBean
    private ModelMapper modelMapper;

    private List<ProjectDTO> projectDTOList;
    private ProjectDTO projectDTO;

    @Before
    public void setUp() {
        projectDTOList = getProjectDTOList();
        projectDTO = projectDTOList.get(0);
    }

    @Test
    public void getAllProjectsTest() throws Exception {
        when(projectFacade.getAllProjects()).thenReturn(projectDTOList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/projects"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProjectById() throws Exception {
        when(projectFacade.getProjectById(1L)).thenReturn(projectDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/projects/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void createProjectWithValidRequestBody() throws Exception {
        when(projectFacade.save(any(ProjectDTO.class))).thenReturn(projectDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\":\"Google\",\n" +
                        "    \"description\":\"Big Data\",\n" +
                        "    \"durationInMonth\":12\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void createProjectFailedWithNoValidRequestBody() throws Exception {
        when(projectFacade.save(any(ProjectDTO.class))).thenReturn(projectDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"\",\n" +
                                "    \"description\":\"\",\n" +
                                "    \"durationInMonth\":-1\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteProjectTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/projects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private List<ProjectDTO> getProjectDTOList() {
        return projects.stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
    }
}