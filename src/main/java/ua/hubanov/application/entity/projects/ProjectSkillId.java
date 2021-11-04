package ua.hubanov.application.entity.projects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ProjectSkillId implements Serializable {

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "skill_id")
    private Long skillId;

    private ProjectSkillId() {}

    public ProjectSkillId(Long projectId, Long skillId) {
        this.projectId = projectId;
        this.skillId = skillId;
    }
}
