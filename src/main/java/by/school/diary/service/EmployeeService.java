package by.school.diary.service;

import by.school.diary.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

//TODO реализовать интерфейс и DTO
public interface EmployeeService extends CRUDService<EmployeeDto, EmployeeDto> {
    String GROUP_NOT_FOUND_PLEASE_REVISE_EMPLOYEE_PARAMETER = "Group not found, please revise Employee parameter";
    String NOT_FOUND_BY_EMPLOYEE_ID = "Subject not found by employee id";
    String ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER = "Employee id is null, please revise employee parameter.";
    String ID_IS_NULL_REVISE_GROUP_PARAMETER = "Group id is null, please revise employee parameter.";

    void delete(EmployeeDto employeeDto);

    GroupDto findBy(EmployeeDto employeeDto);

    GroupDto findById(Long employeeId);

    List<SubjectDto> subjectsBy(EmployeeDto employeeDto);

    List<SubjectDto> subjectsById(Long employeeId);

    List<LessonDto> lessonsBy(EmployeeDto employeeDto);

    Page<LessonDto> lessonsBy(EmployeeDto employeeDto, Pageable pageable);

    Page<LessonDto> lessonsById(Long employeeId, Pageable pageable);

    Page<LessonDto> lessonsBy(EmployeeDto employeeDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<LessonDto> lessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<LessonDto> lessonsBy(Long employeeId, LocalDate from, LocalDate to, Pageable pageable);
}
