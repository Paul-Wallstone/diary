package by.school.diary.repository;

import by.school.diary.entity.ParentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends CrudRepository<ParentEntity, Long> {
}
