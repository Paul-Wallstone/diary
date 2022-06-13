package by.school.diary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import by.school.diary.dto.LessonDto;

import java.util.Date;

public interface LessonService extends CRUDService<LessonDto, LessonDto> {
    Page<LessonDto> findAllByDateBetween(Date from, Date to, Pageable pageable);

    Page<LessonDto> findAllByDateBetweenAndEmployeeId(Date from, Date to, Long employeeId, Pageable pageable);

    Page<LessonDto> findAllByDateBetweenAndSubjectId(Date from, Date to, Long subjectId, Pageable pageable);

    Page<LessonDto> findAllByDateBetweenAndGroupId(Date from, Date to, Long groupId, Pageable pageable);
}
