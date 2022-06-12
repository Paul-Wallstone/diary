package by.school.diary.dto;

import by.school.diary.domain.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class InfoDto {
    private Long id;

    @NotBlank(message = "FirstName is mandatory")
    @Size(min = 2, max = 50, message = "FirstName must be from 2 to 50 characters long")
    private String firstName;

    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, max = 50, message = "LastName must be from 2 to 50 characters long")
    private String lastName;

    @Email
    @Size(max = 70, message = "Email must be not longer 70 characters")
    private String email;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    private String bio;
}
