package ua.hubanov.application.entity.persons;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.entity.enums.Level;
import ua.hubanov.application.entity.enums.Role;
import ua.hubanov.application.entity.enums.Sex;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name="person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ROLE",
        discriminatorType = DiscriminatorType.STRING)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", sequenceName = "person_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String surname;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "role", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PersonSkill> skills = new ArrayList<>();

    public Person() {}

    public Person(String name, String surname, Sex sex, Date dateOfBirth, Role role) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public void addSkill(Skill skill, Level level) {
        PersonSkill personSkill = new PersonSkill(this, skill, level);
        skills.add(personSkill);
        skill.getPersons().add(personSkill);
    }

    public void removeSkill(Skill skill) {
        for (Iterator<PersonSkill> iterator = skills.iterator();
             iterator.hasNext(); ) {
            PersonSkill personSkill = iterator.next();

            if (personSkill.getPerson().equals(this) &&
                    personSkill.getSkill().equals(skill)) {
                iterator.remove();
                personSkill.getSkill().getPersons().remove(personSkill);
                personSkill.setPerson(null);
                personSkill.setSkill(null);
            }
        }
    }
}
