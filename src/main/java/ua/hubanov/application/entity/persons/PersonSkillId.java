package ua.hubanov.application.entity.persons;

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
public class PersonSkillId implements Serializable {

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "skill_id")
    private Long skillId;

    private PersonSkillId() {}

    public PersonSkillId(Long personId, Long skillId) {
        this.personId = personId;
        this.skillId = skillId;
    }
}
