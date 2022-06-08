package by.school.diary.repository;

import by.school.diary.entity.EmployeeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
}
