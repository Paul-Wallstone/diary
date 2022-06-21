package by.school.diary.repository;

import by.school.diary.entity.StudentEntity;
import by.school.diary.entity.StudentLessonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface StudentLessonRepository extends PagingAndSortingRepository<StudentLessonEntity, Long> {

}
