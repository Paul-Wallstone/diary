package by.school.diary.dto;

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
public class ResponseUserDto {
    private Long id;
    @NotBlank(message = "FirstName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String lastName;
    @Size(min = 2, message = "Username must be at least 2 characters long")
    private String userName;
}
