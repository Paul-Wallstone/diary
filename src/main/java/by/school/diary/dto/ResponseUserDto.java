package by.school.diary.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ResponseUserDto {
    private Long id;
    @NotBlank(message = "FirstName is mandatory")
    @Size(min = 1, message = "Name must be at least 2 characters long")
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    @Size(min = 1, message = "Name must be at least 2 characters long")
    private String lastName;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
}
