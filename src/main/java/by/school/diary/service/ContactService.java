package by.school.diary.service;

import by.school.diary.dto.ContactDto;

public interface ContactService extends CRUDService<ContactDto, ContactDto> {
    void delete(ContactDto dto);
}
