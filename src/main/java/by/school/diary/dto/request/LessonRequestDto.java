package by.school.diary.dto.request;

import by.school.diary.entity.EmployeeEntity;
import by.school.diary.entity.GroupEntity;
import by.school.diary.entity.SubjectEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class LessonRequestDto {
    private Long id;

    private LocalDate date;

    private LocalTime timeFrom;

    private LocalTime timeTo;
    
    private String description;

    private SubjectEntity subject;

    private GroupEntity group;

    private EmployeeEntity employee;

}
