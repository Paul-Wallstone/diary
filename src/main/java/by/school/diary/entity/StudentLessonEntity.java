package by.school.diary.entity;

import by.school.diary.domain.Mark;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "student_lesson")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"student"})
public class StudentLessonEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Mark mark;

    @Column(columnDefinition = "text")
    private String notes;

    @Column(columnDefinition = "text")
    private String task;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private StudentEntity student;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    @ToString.Exclude
    private LessonEntity lesson;

    public void setStudent(StudentEntity student) {
        this.student = student;
        this.student.getLessons().add(this);
    }

    public void removeStudent(StudentEntity student) {
        this.student = null;
        student.getLessons().remove(this);
    }

}
