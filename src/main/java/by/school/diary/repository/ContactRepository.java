package by.school.diary.repository;

import by.school.diary.entity.ContactEntity;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<ContactEntity, Long> {
}
