package by.school.diary.repository;

import by.school.diary.entity.LessonEntity;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LessonRepository extends PagingAndSortingRepository<LessonEntity, Long> {
	Page<LessonEntity> findAllByDateBetween(Date from, Date to, Pageable pageable);

    Page<LessonEntity> findAllByDateBetweenAndEmployeeId(Date from, Date to, Long employeeId, Pageable pageable);

    Page<LessonEntity> findAllByDateBetweenAndSubjectId(Date from, Date to, Long subjectId, Pageable pageable);

    Page<LessonEntity> findAllByDateBetweenAndGroupId(Date from, Date to, Long groupId, Pageable pageable);
}
