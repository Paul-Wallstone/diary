package by.school.diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    @NotBlank(message = "FirstName is mandatory")
    @Size(min = 2, message = "FirstName must be at least 2 characters long")
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "LastName must be at least 2 characters long")
    private String lastName;
    @NotBlank(message = "UserName is mandatory")
    @Size(min = 2, message = "UserName must be at least 2 characters long")
    private String username;
}
