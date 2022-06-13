package by.school.diary.service;

import by.school.diary.dto.*;

import java.time.LocalDate;
import java.util.List;

//TODO реализовать интерфейс и DTO
public interface EmployeeService extends CRUDService<EmployeeDto, EmployeeDto> {

    void delete(EmployeeDto employeeDto);

    GroupDto findBy(EmployeeDto employeeDto);

    GroupDto findById(Long employeeId);

    List<SubjectDto> subjectsBy(EmployeeDto employeeDto);

    List<SubjectDto> subjectsById(Long employeeId);

    List<LessonDto> lessonsBy(EmployeeDto employeeDto);

    List<LessonDto> lessonsById(Long employeeId);

    List<LessonDto> lessonsBy(EmployeeDto employeeDto, LocalDate from, LocalDate to);

    List<LessonDto> lessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to);

    List<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto);

    List<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, LocalDate from, LocalDate to);

    List<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to);
}
