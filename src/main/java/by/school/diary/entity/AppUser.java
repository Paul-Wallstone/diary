package by.school.diary.entity;

import by.school.diary.domain.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity()
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String email;
    @NotNull
    private boolean verified;
    @NotNull
    private boolean locked;
    @NotNull
    private boolean expired;
    @NotNull
    private Role role;
}
