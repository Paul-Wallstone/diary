package by.school.diary.service;

import by.school.diary.entity.ContactEntity;

public interface ContactService {
	ContactEntity getById(Long id);

	ContactEntity save(ContactEntity contactEntity);

	ContactEntity update(ContactEntity contactEntity);

    void delete(ContactEntity contactEntity);

    void deleteById(Long id);
}
