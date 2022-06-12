package by.school.diary.service;

import by.school.diary.dto.*;

import java.time.LocalDate;
import java.util.List;

//TODO реализовать интерфейс и DTO
public interface GroupService extends CRUDService<GroupDto, GroupDto> {

    void delete(GroupDto groupDto);

    void deleteAllBy(GroupDto groupDto);

    List<StudentDto> studentsBy(GroupDto groupDto);

    List<StudentDto> studentsById(Long groupId);

    List<LessonDto> lessonsBy(GroupDto groupDto);

    List<LessonDto> lessonsById(Long groupId);

    List<LessonDto> lessonsBy(GroupDto groupDto, LocalDate from, LocalDate to);

    List<LessonDto> lessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to);

    List<StudentLessonDto> studentLessonsBy(GroupDto groupDto);

    List<StudentLessonDto> studentLessonsBy(GroupDto groupDto, LocalDate from, LocalDate to);

    List<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to);
}
