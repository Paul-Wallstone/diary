package by.school.diary.entity;

import by.school.diary.domain.Mark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "schedules")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Date date;
    private Mark mark;
    private String message;
    @OneToOne
    private SubjectEntity subject;
    @OneToOne
    private EmployeeEntity employee;
    @ManyToMany(mappedBy = "schedules")
    private Set<DiaryEntity> diaries;
}
