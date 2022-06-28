package by.school.diary.repository;

import by.school.diary.entity.StudentLessonEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentLessonRepository extends PagingAndSortingRepository<StudentLessonEntity, Long> {
    List<StudentLessonEntity> findAllByLessonId(Long lessonId, Pageable pageable);

}
