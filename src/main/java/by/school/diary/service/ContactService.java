package by.school.diary.service;

import by.school.diary.dto.request.ContactDto;

public interface ContactService extends CRUDService<ContactDto, ContactDto> {

    void delete(ContactDto contactDto);
}
