package by.school.diary.dto.response;

import by.school.diary.domain.Mark;
import by.school.diary.entity.DiaryEntity;
import by.school.diary.entity.EmployeeEntity;
import by.school.diary.entity.GroupEntity;
import by.school.diary.entity.SubjectEntity;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class LessonResponseDto {
    private Long id;

    private Date date;

    private Mark mark;

    private String message;

    private SubjectEntity subject;

    private GroupEntity group;

    private EmployeeEntity employee;

    private Set<DiaryEntity> diaries;
}
