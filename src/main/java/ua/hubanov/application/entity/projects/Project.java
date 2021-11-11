package ua.hubanov.application.entity.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.entity.enums.Level;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "Project")
@Table(name = "project")
@JsonIgnoreProperties(value = { "skills" })
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_generator")
    @SequenceGenerator(name = "project_generator", sequenceName = "project_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    private int durationInMonth;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProjectSkill> skills = new ArrayList<>();

    public Project() {}

    public Project(String name, String description, int durationInMonth) {
        this.name = name;
        this.description = description;
        this.durationInMonth = durationInMonth;
    }

    public void addSkill(Skill skill, Level level) {
        ProjectSkill projectSkill = new ProjectSkill(this, skill, level);
        skills.add(projectSkill);
        skill.getProjects().add(projectSkill);
    }

    public void removeSkill(Skill skill) {
        for (Iterator<ProjectSkill> iterator = skills.iterator();
             iterator.hasNext(); ) {
            ProjectSkill projectSkill = iterator.next();

            if (projectSkill.getProject().equals(this) &&
                    projectSkill.getSkill().equals(skill)) {
                iterator.remove();
                projectSkill.getSkill().getProjects().remove(projectSkill);
                projectSkill.setProject(null);
                projectSkill.setSkill(null);
            }
        }
    }
}
