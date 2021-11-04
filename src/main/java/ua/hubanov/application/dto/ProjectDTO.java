package ua.hubanov.application.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ProjectDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String name;

    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String description;

    @Min(value = 1, message="duration in month: positive number, min 1 is required")
    private int durationInMonth;
}
