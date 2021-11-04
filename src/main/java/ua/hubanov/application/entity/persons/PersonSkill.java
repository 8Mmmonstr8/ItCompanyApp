package ua.hubanov.application.entity.persons;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.entity.enums.Level;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "PersonSkill")
@Table(name = "person_skill")
public class PersonSkill {

    @EmbeddedId
    private PersonSkillId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    private Skill skill;

    @Column(name = "level")
    private Level level;

    private PersonSkill() {}

    public PersonSkill(Person person, Skill skill, Level level) {
        this.id = new PersonSkillId(person.getId(), skill.getId());
        this.person = person;
        this.skill = skill;
        this.level = level;
    }
}
