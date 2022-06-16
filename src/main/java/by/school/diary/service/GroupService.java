package by.school.diary.service;

import by.school.diary.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

//TODO реализовать интерфейс и DTO
public interface GroupService extends CRUDService<GroupDto, GroupDto> {

    void delete(GroupDto groupDto);

    void deleteAllBy(GroupDto groupDto);

    Page<StudentDto> studentsBy(GroupDto groupDto, Pageable pageable);

    Page<StudentDto> studentsById(Long groupId, Pageable pageable);

    Page<LessonDto> lessonsBy(GroupDto groupDto, Pageable pageable);

    Page<LessonDto> lessonsById(Long groupId, Pageable pageable);

    Page<LessonDto> lessonsBy(GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<LessonDto> lessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(GroupDto groupDto, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);
}
