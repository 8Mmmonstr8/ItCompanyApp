package ua.hubanov.application;

import ua.hubanov.application.entity.projects.Project;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractProjectTest {

    protected List<Project> projects = createProjects();

    private static List<Project> createProjects() {
        List<Project> projectList = new ArrayList<>();

        Project project1 = new Project();
        project1.setId(1L);
        project1.setName("Google");
        project1.setDescription("Big Data");
        project1.setDurationInMonth(12);
        projectList.add(project1);

        Project project2 = new Project();
        project2.setId(2L);
        project2.setName("Amazon");
        project2.setDescription("Big Data");
        project2.setDurationInMonth(12);
        projectList.add(project2);

        Project project3 = new Project();
        project3.setId(3L);
        project3.setName("DHL");
        project3.setDescription("Logistic");
        project3.setDurationInMonth(24);
        projectList.add(project3);

        Project project4 = new Project();
        project4.setId(4L);
        project4.setName("Alibaba");
        project4.setDescription("Logistic");
        project4.setDurationInMonth(24);
        projectList.add(project4);

        Project project5 = new Project();
        project5.setId(5L);
        project5.setName("Xiaomi");
        project5.setDescription("Big Data");
        project5.setDurationInMonth(36);
        projectList.add(project5);

        return projectList;
    }
}
