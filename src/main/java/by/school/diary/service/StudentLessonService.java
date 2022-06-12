package by.school.diary.service;

import by.school.diary.dto.EmployeeDto;
import by.school.diary.dto.StudentDto;
import by.school.diary.dto.StudentLessonDto;

import java.util.List;

//TODO реализовать интерфейс и DTO +добавить свои методы
public interface StudentLessonService extends CRUDService<StudentLessonDto, StudentLessonDto> {

    List<StudentDto> studentsBy(StudentLessonDto studentLessonDto);

    List<StudentDto> studentsById(Long id);

    EmployeeDto employeeBy(StudentLessonDto studentLessonDto);

    EmployeeDto employeeById(Long id);

}
