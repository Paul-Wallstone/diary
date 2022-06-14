package by.school.diary.dto;

import by.school.diary.domain.Sex;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Builder
@Data
public class InfoDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Sex sex;

    private LocalDate birthday;

    private String bio;
}
