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
@EqualsAndHashCode(callSuper = true, exclude = {"subjects", "info", "contact", "position", "user", "lessons", "institution"})
public class EmployeeEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id")
    @ToString.Exclude
    private InfoEntity info;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    private ContactEntity contact;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "position_id")
    @ToString.Exclude
    private PositionEntity position;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @ToString.Exclude
    private UserEntity user;

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    @Builder.Default
    private Set<SubjectEntity> subjects = new HashSet<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "employee", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private Set<LessonEntity> lessons = new HashSet<>();

    @ManyToOne
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
