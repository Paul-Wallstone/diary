package by.school.diary.repository;

import by.school.diary.entity.InstitutionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InstitutionRepository extends PagingAndSortingRepository<InstitutionEntity, Long> {
}
