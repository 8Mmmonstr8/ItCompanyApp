package ua.hubanov.application.entity.persons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ua.hubanov.application.entity.enums.Role;
import ua.hubanov.application.entity.enums.Sex;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@DiscriminatorValue(value=Role.Values.DEVELOPER)
@JsonIgnoreProperties(value = { "skills" })
public class Developer extends Person {

    public Developer() {}

    public Developer(String name, String surname, Sex sex, Date dateOfBirth) {
        super(name, surname, sex, dateOfBirth, Role.DEVELOPER);
    }
}
