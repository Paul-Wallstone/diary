package by.school.diary.service;

import by.school.diary.dto.EmployeeDto;
import by.school.diary.dto.PositionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



//TODO реализовать интерфейс и DTO +добавить свои методы
public interface PositionService extends CRUDService<PositionDto, PositionDto> {

    void delete(PositionDto positionDto);

    Page<EmployeeDto> allEmployeeBy(PositionDto positionDto, Pageable pageable);

}
