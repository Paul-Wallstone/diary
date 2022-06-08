package by.school.diary.service;

import by.school.diary.dto.EmployeeDto;
import by.school.diary.dto.SubjectDto;
import by.school.diary.entity.SubjectEntity;

import java.util.List;

//TODO реализовать интерфейс и DTO
public interface SubjectService extends CRUDService<SubjectDto, SubjectEntity> {

    void delete(SubjectDto subjectDto);

    void deleteAllByEmployee(EmployeeDto employeeDto);

    List<SubjectDto> findAllByEmployee(EmployeeDto employeeDto);

    List<SubjectDto> findAllByEmployeeId(Long employeeId);

}
