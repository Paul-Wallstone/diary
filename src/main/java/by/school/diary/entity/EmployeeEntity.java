package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = "subjects")
public class EmployeeEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "info_id")
    @ToString.Exclude
    private InfoEntity info;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @ToString.Exclude
    private final Set<SubjectEntity> subjects = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "position_id")
    @ToString.Exclude
    private PositionEntity position;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    @ToString.Exclude
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    private ContactEntity contact;

    public void addSubject(SubjectEntity subject) {
        this.subjects.add(subject);
        subject.getEmployees().add(this);
    }

    public void removeSubject(SubjectEntity subject) {
        this.subjects.remove(subject);
        subject.getEmployees().remove(this);
    }

    public void removeBooks() {
        for (SubjectEntity subject : this.subjects) {
            subject.getEmployees().remove(this);
        }
    }
}
