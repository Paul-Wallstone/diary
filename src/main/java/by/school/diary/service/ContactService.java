package by.school.diary.service;

import by.school.diary.dto.request.ContactRequestDto;
import by.school.diary.dto.response.ContactResponseDto;

public interface ContactService {
	ContactResponseDto getById(Long id);

	ContactResponseDto save(ContactRequestDto contactDto);

	ContactResponseDto update(ContactRequestDto contactDto, Long id);

	void delete(ContactRequestDto contactDto);

	void deleteById(Long id);
}
