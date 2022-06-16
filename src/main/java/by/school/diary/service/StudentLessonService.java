package by.school.diary.service;

import by.school.diary.dto.EmployeeDto;
import by.school.diary.dto.StudentDto;
import by.school.diary.dto.StudentLessonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


//TODO реализовать интерфейс и DTO +добавить свои методы
public interface StudentLessonService extends CRUDService<StudentLessonDto, StudentLessonDto> {

    Page<StudentDto> studentsBy(StudentLessonDto studentLessonDto, Pageable pageable);

    Page<StudentDto> studentsById(Long id, Pageable pageable);

    EmployeeDto employeeBy(StudentLessonDto studentLessonDto);

    EmployeeDto employeeById(Long id);

}
