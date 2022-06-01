package by.school.diary.entity;

import by.school.diary.domain.Mark;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "lessons")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"subject", "group", "employee", "diaries"})
public class LessonEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @NotNull
    private Date date;

    private Mark mark;

    @Column(columnDefinition = "text")
    private String message;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @NotNull
    @ToString.Exclude
    private SubjectEntity subject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @NotNull
    @ToString.Exclude
    private GroupEntity group;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @ToString.Exclude
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    @ToString.Exclude
    private  DiaryEntity diary;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private StudentEntity student;
}
