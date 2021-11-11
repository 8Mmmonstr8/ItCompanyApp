package ua.hubanov.application.entity.persons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ua.hubanov.application.entity.enums.Role;
import ua.hubanov.application.entity.enums.Sex;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@Entity
@DiscriminatorValue(value= Role.Values.BA)
@JsonIgnoreProperties(value = { "skills" })
public class Ba extends Person {

    public Ba() {}

    public Ba(String name, String surname, Sex sex, Date dateOfBirth) {
        super(name, surname, sex, dateOfBirth, Role.BA);
    }
}
