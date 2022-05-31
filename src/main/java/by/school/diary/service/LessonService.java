package by.school.diary.service;


import by.school.diary.dto.request.LessonRequestDto;
import by.school.diary.dto.response.LessonResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface LessonService extends CRUDService<LessonResponseDto, LessonRequestDto> {
    Page<LessonResponseDto> findAllByDateBetween(Date from, Date to, Pageable pageable);

    Page<LessonResponseDto> findAllByDateBetweenAndEmployeeId(Date from, Date to, Long employeeId, Pageable pageable);

    Page<LessonResponseDto> findAllByDateBetweenAndSubjectId(Date from, Date to, Long subjectId, Pageable pageable);

    Page<LessonResponseDto> findAllByDateBetweenAndGroupId(Date from, Date to, Long groupId, Pageable pageable);
}
