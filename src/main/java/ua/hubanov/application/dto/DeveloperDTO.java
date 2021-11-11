package ua.hubanov.application.dto;

import lombok.Data;
import ua.hubanov.application.entity.enums.Role;
import ua.hubanov.application.entity.enums.Sex;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class DeveloperDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String name;

    @NotEmpty
    @Size(min = 2, message = "user surname should have at least 2 characters")
    private String surname;

    private Sex sex;

    private Date dateOfBirth;

    private Role role;
}
