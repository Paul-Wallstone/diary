package by.school.diary.repository;

import by.school.diary.entity.GroupEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<GroupEntity, Long> {
}
