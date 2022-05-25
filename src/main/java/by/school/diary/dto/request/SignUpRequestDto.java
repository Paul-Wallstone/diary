package by.school.diary.dto.request;

import by.school.diary.annotations.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignUpRequestDto {
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;

    @NotEmpty(message = "Please enter your name")
    private String firstName;

    @NotEmpty(message = "Please enter your lastname")
    private String lastName;

    @NotEmpty(message = "Please enter your username")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 12, message = "Password must be from 6 to 12 characters long")
    private String password;

    private String confirmPassword;
}
