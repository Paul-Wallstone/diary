package by.school.diary.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RequestUserDto {
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
    @NotBlank(message = "Password is mandatory")
    @Size(min = 5, max = 12, message = "Password must be from 2 to 12 characters long")
    private String password;
}
