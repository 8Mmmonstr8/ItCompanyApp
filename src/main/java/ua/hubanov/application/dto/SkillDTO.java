package ua.hubanov.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SkillDTO {

    private Long id;

    @NotEmpty
    @Size(min = 1, message = "user name should have at least 1 character")
    private String name;
}
