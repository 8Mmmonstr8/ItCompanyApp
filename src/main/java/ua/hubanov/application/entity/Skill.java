package ua.hubanov.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.hubanov.application.entity.persons.PersonSkill;
import ua.hubanov.application.entity.projects.ProjectSkill;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "Skill")
@Table(name = "skill")
@JsonIgnoreProperties(value = { "projects", "persons" })
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_generator")
    @SequenceGenerator(name = "skill_generator", sequenceName = "skill_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "skill",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProjectSkill> projects = new ArrayList<>();

    @OneToMany(
            mappedBy = "skill",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PersonSkill> persons = new ArrayList<>();

    public Skill() {}

    public Skill(String name) {
        this.name = name;
    }
}