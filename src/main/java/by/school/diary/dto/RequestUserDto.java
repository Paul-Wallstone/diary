package by.school.diary.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RequestUserDto {
    @NotBlank(message = "FirstName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String lastName;
    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "Username must be at least 2 characters long")
    private String userName;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 3, max = 12, message = "Password must be from 3 to 12 characters long")
    private String password;
}
