package by.school.diary.service;

import by.school.diary.dto.InfoDto;

public interface InfoService extends CRUDService<InfoDto, InfoDto> {
    void delete(InfoDto dto);
}
