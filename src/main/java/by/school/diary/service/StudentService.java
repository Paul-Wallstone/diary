package by.school.diary.service;


import by.school.diary.dto.*;

import java.time.LocalDate;
import java.util.List;

//TODO реализовать интерфейс и DTO
public interface StudentService extends CRUDService<StudentDto, StudentDto> {

    void delete(StudentDto studentDto);

    void deleteAllBy(StudentDto studentDto);

    List<ParentDto> parentsBy(StudentDto studentDto);

    List<ParentDto> parentsById(Long studentId);

    List<LessonDto> lessonsById(Long studentId);

    List<LessonDto> lessonsBy(StudentDto studentDto);

    List<LessonDto> lessonsBy(StudentDto studentDto, LocalDate from, LocalDate to);

    List<LessonDto> lessonsBy(StudentDto studentDto, GroupDto groupDto, LocalDate from, LocalDate to);

    List<StudentLessonDto> studentLessonsBy(StudentDto studentDto);

    List<StudentLessonDto> studentLessonsBy(StudentDto studentDto, LocalDate from, LocalDate to);

    List<StudentLessonDto> studentLessonsBy(StudentDto studentDto, GroupDto groupDto, LocalDate from, LocalDate to);

}
