package by.school.diary.repository;

import by.school.diary.entity.LessonEntity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LessonRepository extends PagingAndSortingRepository<LessonEntity, Long> {
    Page<LessonEntity> findAllByDateBetween(LocalDate from, LocalDate to, Pageable pageable);

    Page<LessonEntity> findAllByDateBetweenAndEmployeeId(LocalDate from, LocalDate to, Long employeeId, Pageable pageable);

    Page<LessonEntity> findAllByDateBetweenAndEmployeeIdAndGroupId(LocalDate from, LocalDate to, Long employeeId, Long groupId, Pageable pageable);

    Page<LessonEntity> findAllByEmployeeId(Long employeeId, Pageable pageable);

    List<LessonEntity> findAllByEmployeeId(Long employeeId);

    List<LessonEntity> findAllByDateBetweenAndEmployeeIdAndGroupId(LocalDate from, LocalDate to, Long employeeId, Long groupId);

    List<LessonEntity> findAllByDateBetweenAndEmployeeId(LocalDate from, LocalDate to, Long employeeId);

    Page<LessonEntity> findAllByDateBetweenAndSubjectId(LocalDate from, LocalDate to, Long subjectId, Pageable pageable);

    Page<LessonEntity> findAllByDateBetweenAndGroupId(LocalDate from, LocalDate to, Long groupId, Pageable pageable);


}
