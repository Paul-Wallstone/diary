package by.school.diary.service;

import by.school.diary.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

//TODO реализовать интерфейс и DTO
public interface EmployeeService extends CRUDService<EmployeeDto, EmployeeDto> {

    void delete(EmployeeDto employeeDto);

    GroupDto findBy(EmployeeDto employeeDto);

    GroupDto findById(Long employeeId);

    Page<SubjectDto> subjectsBy(EmployeeDto employeeDto, Pageable pageable);

    Page<SubjectDto> subjectsById(Long employeeId, Pageable pageable);

    Page<LessonDto> lessonsBy(EmployeeDto employeeDto, Pageable pageable);

    Page<LessonDto> lessonsById(Long employeeId, Pageable pageable);

    Page<LessonDto> lessonsBy(EmployeeDto employeeDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<LessonDto> lessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);
}
