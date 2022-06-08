package by.school.diary.dto.request;

import by.school.diary.domain.Mark;
import by.school.diary.entity.EmployeeEntity;
import by.school.diary.entity.GroupEntity;
import by.school.diary.entity.SubjectEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class LessonRequestDto {
    private Long id;

    private LocalDate date;

    private Mark mark;

    private String message;

    private SubjectEntity subject;

    private GroupEntity group;

    private EmployeeEntity employee;

}
