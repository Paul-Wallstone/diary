package by.school.diary.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "diaries")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"lessons"})
public class DiaryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @ToString.Exclude
    private final Set<LessonEntity> lessons = new HashSet<>();

    public void addLesson(LessonEntity lesson) {
        this.lessons.add(lesson);
        lesson.getDiaries().add(this);
    }

    public void removeLesson(LessonEntity lesson) {
        this.lessons.remove(lesson);
        lesson.getDiaries().remove(this);
    }

    public void removeLessons() {
        for (LessonEntity lesson : this.lessons) {
            lesson.getDiaries().remove(this);
        }
    }
}
