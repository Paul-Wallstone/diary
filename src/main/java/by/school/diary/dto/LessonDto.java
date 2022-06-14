package by.school.diary.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import by.school.diary.entity.EmployeeEntity;
import by.school.diary.entity.GroupEntity;
import by.school.diary.entity.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {

	private Long id;

    private LocalDate date;

    private LocalTime timeFrom;

    private LocalTime timeTo;
    
    private String description;

    private SubjectEntity subject;

    private GroupEntity group;

    private EmployeeEntity employee;
}
