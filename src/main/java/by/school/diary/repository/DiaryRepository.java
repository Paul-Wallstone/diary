package by.school.diary.repository;

import by.school.diary.entity.DiaryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends CrudRepository<DiaryEntity, Long> {
}
