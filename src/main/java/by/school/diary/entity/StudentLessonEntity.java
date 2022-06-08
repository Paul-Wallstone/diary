package by.school.diary.entity;

import by.school.diary.domain.Mark;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "student_lesson",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"lesson_id", "student_id"})})
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"student"})
public class StudentLessonEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder
    public StudentLessonEntity(Mark mark, String notes, String task, StudentEntity student, LessonEntity lesson) {
        this.mark = mark;
        this.notes = notes;
        this.task = task;
        this.student = student;
        this.student.getLessons().add(this);
        this.lesson = lesson;
        this.lesson.getLessons().add(this);
    }

    private Mark mark;

    @Column(columnDefinition = "text")
    private String notes;

    @Column(columnDefinition = "text")
    private String task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    @ToString.Exclude
    private LessonEntity lesson;

}
