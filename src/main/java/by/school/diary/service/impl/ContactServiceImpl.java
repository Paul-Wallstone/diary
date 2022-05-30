package by.school.diary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.school.diary.entity.ContactEntity;
import by.school.diary.exception.ContactNotFoundException;
import by.school.diary.repository.ContactRepository;
import by.school.diary.service.ContactService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public ContactEntity getById(Long id) {
		return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
	}

	@Override
	public ContactEntity save(ContactEntity contactEntity) {
		return contactRepository.save(contactEntity);
	}

	@Override
	public ContactEntity update(ContactEntity contactEntity) {
		return contactRepository.save(contactEntity);
	}

	@Override
	public void delete(ContactEntity contactEntity) {
		if (contactRepository.findByContactEntity(contactEntity) != null) {
			contactRepository.delete(contactEntity);
		} else {
			throw new ContactNotFoundException(contactEntity.getId());
		}
	}

	@Override
	public void deleteById(Long id) {
		if (contactRepository.existsById(id)) {
			contactRepository.deleteById(id);
		} else {
			throw new ContactNotFoundException(id);
		}
	}

}
