package by.school.diary.repository;

import by.school.diary.entity.ContactEntity;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Long> {
	Optional<ContactEntity> findByContactEntity(ContactEntity contactEntity);
}
