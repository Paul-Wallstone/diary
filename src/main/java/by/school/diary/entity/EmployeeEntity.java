package by.school.diary.entity;

import by.school.diary.domain.Role;
import lombok.*;
import org.hibernate.annotations.Proxy;
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
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"subjects", "position", "lessons", "institution"})
@PrimaryKeyJoinColumn(name = "id")
public class EmployeeEntity extends UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder(builderMethodName = "EBuilder")
    public EmployeeEntity(InfoEntity info, ContactEntity contact, @NotBlank(message = "UserName is mandatory") @Size(min = 2, message = "UserName must be at least 2 characters long") String username, @NotBlank(message = "Password is mandatory") String password, boolean verified, boolean locked, boolean credentialsExpired, boolean accountExpired, boolean enabled, Set<Role> roles, Collection<? extends GrantedAuthority> authorities, PositionEntity position, Set<SubjectEntity> subjects, InstitutionEntity institution) {
        super(info, contact, username, password, verified, locked, credentialsExpired, accountExpired, enabled, roles, authorities);
        this.position = position;
        this.subjects = subjects;
        this.institution = institution;
    }

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "position_id")
    @ToString.Exclude
    private PositionEntity position;

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    private Set<SubjectEntity> subjects = new HashSet<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "employee", orphanRemoval = true)
    @ToString.Exclude
    private Set<LessonEntity> lessons = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institution_id")
    @ToString.Exclude
    private InstitutionEntity institution;

    public void setSubject(SubjectEntity subject) {
        this.subjects.add(subject);
        subject.setEmployee(this);
    }

    public void removeSubject(SubjectEntity subject) {
        this.subjects.remove(subject);
        subject.setEmployee(null);
    }

    public void removeSubjects() {
        for (SubjectEntity subject : this.subjects) {
            subject.setEmployee(null);
        }
    }

    public void setLesson(LessonEntity lesson) {
        this.lessons.add(lesson);
        lesson.setEmployee(this);
    }

    public void removeLesson(LessonEntity lesson) {
        this.lessons.remove(lesson);
        lesson.setEmployee(null);
    }

    public void removeLessons() {
        for (LessonEntity lesson : this.lessons) {
            lesson.setEmployee(null);
        }
    }
}
