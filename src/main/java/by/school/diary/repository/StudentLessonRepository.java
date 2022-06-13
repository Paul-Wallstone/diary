package by.school.diary.repository;

import by.school.diary.entity.StudentLessonEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface StudentLessonRepository extends PagingAndSortingRepository<StudentLessonEntity, Long> {
}
