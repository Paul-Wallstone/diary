package by.school.diary.repository;

import by.school.diary.entity.EmployeeEntity;
import by.school.diary.entity.GroupEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<GroupEntity, Long> {
    Optional<GroupEntity> findByEmployeeId(Long id);

    Optional<GroupEntity> findByEmployee(EmployeeEntity employee);
}
