package by.school.diary.entity;

import by.school.diary.domain.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"group", "parents"})
@PrimaryKeyJoinColumn(name = "id")
public class StudentEntity extends UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder(builderMethodName = "SBuilder")
    public StudentEntity(InfoEntity info, ContactEntity contact, @NotBlank(message = "UserName is mandatory") @Size(min = 2, message = "UserName must be at least 2 characters long") String username, @NotBlank(message = "Password is mandatory") String password, boolean verified, boolean locked, boolean credentialsExpired, boolean accountExpired, boolean enabled, Set<Role> roles, Collection<? extends GrantedAuthority> authorities, GroupEntity group) {
        super(info, contact, username, password, verified, locked, credentialsExpired, accountExpired, enabled, roles, authorities);
        this.group = group;
     }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private GroupEntity group;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "student", orphanRemoval = true)
    @ToString.Exclude
    private final Set<ParentEntity> parents = new HashSet<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "student")
    @ToString.Exclude
    private Set<StudentLessonEntity> lessons = new HashSet<>();

    public void setParent(ParentEntity parent) {
        this.parents.add(parent);
        parent.setStudent(this);
    }

    public void removeParent(ParentEntity parent) {
        parent.setStudent(null);
        this.parents.remove(parent);
    }

    public void removeParent() {
        for (ParentEntity parent : this.parents) {
            parent.setStudent(null);
        }
    }
}
