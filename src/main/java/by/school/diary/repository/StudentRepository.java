package by.school.diary.repository;

import by.school.diary.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {
    Page<StudentEntity> findAllByGroupId (Long id, Pageable pageble);
}
