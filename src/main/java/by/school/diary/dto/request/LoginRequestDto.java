package by.school.diary.dto.request;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LoginRequestDto {
    @NotEmpty(message = "Username can not be empty")
    private String username;
    @NotEmpty(message = "Password can not be empty")
    @Size(min = 6, max = 12, message = "Password must be from 6 to 12 characters long")
    private String password;
}
