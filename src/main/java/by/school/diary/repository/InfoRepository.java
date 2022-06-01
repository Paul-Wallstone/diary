package by.school.diary.repository;

import by.school.diary.entity.InfoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InfoRepository extends PagingAndSortingRepository<InfoEntity, Long> {
}
