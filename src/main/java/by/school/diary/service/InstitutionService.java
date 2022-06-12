package by.school.diary.service;

import by.school.diary.dto.EmployeeDto;
import by.school.diary.dto.GroupDto;
import by.school.diary.dto.InstitutionDto;
import by.school.diary.dto.PositionDto;

import java.util.List;

//TODO реализовать интерфейс и DTO +добавить свои методы
public interface InstitutionService extends CRUDService<InstitutionDto, InstitutionDto> {

    void delete(PositionDto positionDto);

    List<EmployeeDto> allEmployeeBy(InstitutionDto institutionDto);

    List<GroupDto> allGroupsBy(InstitutionDto institutionDto);

}
