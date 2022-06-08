package by.school.diary.entity;

import by.school.diary.domain.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


@Entity
@Data
@Table(name = "parents")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"student"})
public class ParentEntity extends UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder(builderMethodName = "PBuilder")
    public ParentEntity(InfoEntity info, ContactEntity contact, @NotBlank(message = "UserName is mandatory") @Size(min = 2, message = "UserName must be at least 2 characters long") String username, @NotBlank(message = "Password is mandatory") String password, boolean verified, boolean locked, boolean credentialsExpired, boolean accountExpired, boolean enabled, Set<Role> roles, Collection<? extends GrantedAuthority> authorities, StudentEntity student) {
        super(info, contact, username, password, verified, locked, credentialsExpired, accountExpired, enabled, roles, authorities);
        this.student = student;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private StudentEntity student;

}
