package by.school.diary.entity;

import by.school.diary.domain.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "users")
@Table()
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Column(nullable = false, length = 50, unique = true)
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    @Column(length = 70, unique = true, nullable = true)
    @Email
    private String email;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean verified;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean locked;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean expired;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 35)
    private Role role;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
