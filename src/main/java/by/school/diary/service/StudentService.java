package by.school.diary.service;


import by.school.diary.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

//TODO реализовать интерфейс и DTO
public interface StudentService extends CRUDService<StudentDto, StudentDto> {

    void delete(StudentDto studentDto);

    void deleteAllBy(StudentDto studentDto);

    Page<ParentDto> parentsBy(StudentDto studentDto, Pageable pageable);

    Page<ParentDto> parentsById(Long studentId, Pageable pageable);

    Page<LessonDto> lessonsById(Long studentId, Pageable pageable);

    Page<LessonDto> lessonsBy(StudentDto studentDto, Pageable pageable);

    Page<LessonDto> lessonsBy(StudentDto studentDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<LessonDto> lessonsBy(StudentDto studentDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(StudentDto studentDto, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(StudentDto studentDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(StudentDto studentDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

}
