package by.school.diary.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRequestDto {
    @NotBlank(message = "FirstName is mandatory")
    @Size(min = 2, message = "FirstName must be at least 2 characters long")
    private String firstname;
    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "LastName must be at least 2 characters long")
    private String lastname;
    @NotBlank(message = "UserName is mandatory")
    @Size(min = 2, message = "UserName must be at least 2 characters long")
    private String username;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 12, message = "Password must be from 6 to 12 characters long")
    private String password;
}