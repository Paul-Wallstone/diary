package by.school.diary.repository;

import by.school.diary.entity.SubjectEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubjectRepository extends PagingAndSortingRepository<SubjectEntity, Long> {
}
