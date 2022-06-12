package by.school.diary.service;

import by.school.diary.dto.EmployeeDto;
import by.school.diary.dto.PositionDto;


import java.util.List;

//TODO реализовать интерфейс и DTO +добавить свои методы
public interface PositionService extends CRUDService<PositionDto, PositionDto> {

    void delete(PositionDto positionDto);

    List<EmployeeDto> allEmployeeBy(PositionDto positionDto);

}
