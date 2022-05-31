package by.school.diary.repository;


import by.school.diary.entity.PositionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PositionRepository extends PagingAndSortingRepository<PositionEntity, Long> {
}
