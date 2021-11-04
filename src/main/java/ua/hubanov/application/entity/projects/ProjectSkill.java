package ua.hubanov.application.entity.projects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.entity.enums.Level;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "ProjectSkill")
@Table(name = "project_skill")
public class ProjectSkill {

    @EmbeddedId
    private ProjectSkillId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    private Skill skill;

    @Column(name = "level")
    private Level level;

    private ProjectSkill() {}

    public ProjectSkill(Project project, Skill skill, Level level) {
        this.id = new ProjectSkillId(project.getId(), skill.getId());
        this.project = project;
        this.skill = skill;
        this.level = level;
    }
}
