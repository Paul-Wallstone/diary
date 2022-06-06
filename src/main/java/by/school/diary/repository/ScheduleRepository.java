package by.school.diary.repository;

import by.school.diary.entity.ScheduleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ScheduleRepository extends PagingAndSortingRepository<ScheduleEntity, Long> {

}
