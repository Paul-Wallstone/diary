package by.school.diary.entity;

import by.school.diary.domain.Mark;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity(name = "diaries")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"lessons"})
public class DiaryEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "student_id")
    @NotNull
    @ToString.Exclude
    private StudentEntity student;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "diary", orphanRemoval = true)
    @ToString.Exclude
    private final Set<LessonEntity> lessons = new HashSet<>();

    public void addLesson(LessonEntity lesson) {
        this.lessons.iterator();
        lesson.setStudent(student);
        this.lessons.add(lesson);
    }

    public void setMark(Mark mark, Long id) {
        this.lessons.iterator();
        this.lessons.stream().forEach(lesson -> {
            if (lesson.getId().equals(id))
                lesson.setMark(mark);
        });
    }


    public void removeLesson(LessonEntity lesson) {
        this.lessons.remove(lesson);
    }

    public void removeLessons() {
        for (LessonEntity lesson : this.lessons) {
            lesson.setStudent(null);
            lesson.setDiary(null);
        }
    }
}
