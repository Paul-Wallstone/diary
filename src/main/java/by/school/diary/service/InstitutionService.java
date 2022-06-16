package by.school.diary.service;

import by.school.diary.dto.EmployeeDto;
import by.school.diary.dto.GroupDto;
import by.school.diary.dto.InstitutionDto;
import by.school.diary.dto.PositionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


//TODO реализовать интерфейс и DTO +добавить свои методы
public interface InstitutionService extends CRUDService<InstitutionDto, InstitutionDto> {

    void delete(PositionDto positionDto);

    Page<EmployeeDto> allEmployeeBy(InstitutionDto institutionDto, Pageable pageable);

    Page<GroupDto> allGroupsBy(InstitutionDto institutionDto, Pageable pageable);

}
