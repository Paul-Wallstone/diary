package by.school.diary.entity;

import by.school.diary.domain.Role;
import lombok.*;

import javax.persistence.*;

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
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(length = 70)
    private String email;
    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean verified;
    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean locked;
    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean expired;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Role role;
}
