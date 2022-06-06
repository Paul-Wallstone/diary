package by.school.diary.repository;

import by.school.diary.entity.LessonEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LessonRepository extends PagingAndSortingRepository<LessonEntity, Long> {
}
