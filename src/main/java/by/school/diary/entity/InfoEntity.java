package by.school.diary.entity;

import by.school.diary.domain.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InfoEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

//    @NotBlank(message = "FirstName is mandatory")
//    @Size(min = 2, message = "FirstName must be at least 2 characters long")
//    @Column(nullable = false, length = 50)
    private String firstName;

//    @NotBlank(message = "LastName is mandatory")
//    @Size(min = 2, message = "LastName must be at least 2 characters long")
//    @Column(nullable = false, length = 50)
    private String lastName;

//    @Column(length = 70, unique = true, nullable = false)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Sex sex;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate birthday;

    @Column(columnDefinition = "text")
    private String bio;

}
