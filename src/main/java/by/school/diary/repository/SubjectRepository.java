package by.school.diary.repository;

import by.school.diary.entity.SubjectEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends PagingAndSortingRepository<SubjectEntity, Long> {
    Optional<List<SubjectEntity>> findByEmployeeId(Long employeeId);
}
