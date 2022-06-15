package by.school.diary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import by.school.diary.dto.LessonDto;

import java.time.LocalDate;

public interface LessonService extends CRUDService<LessonDto, LessonDto> {
    Page<LessonDto> allByDates(LocalDate from, LocalDate to, Pageable pageable);

    Page<LessonDto> allByDatesAndEmployeeId(LocalDate from, LocalDate to, Long employeeId, Pageable pageable);

    Page<LessonDto> allByDatesAndSubjectId(LocalDate from, LocalDate to, Long subjectId, Pageable pageable);

    Page<LessonDto> allByDatesAndGroupId(LocalDate from, LocalDate to, Long groupId, Pageable pageable);
}
