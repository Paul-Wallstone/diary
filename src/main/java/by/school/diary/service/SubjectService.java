package by.school.diary.service;

import by.school.diary.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;


//TODO реализовать интерфейс и DTO + свои методы
public interface SubjectService extends CRUDService<SubjectDto, SubjectDto> {

    void delete(SubjectDto subjectDto);

    Page<LessonDto> lessonsBy(SubjectDto subjectDto, Pageable pageable);

    Page<LessonDto> lessonsBy(SubjectDto subjectDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<LessonDto> lessonsBy(SubjectDto subjectDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<LessonDto> lessonsBy(SubjectDto subjectDto, StudentDto studentDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(SubjectDto subjectDto, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(SubjectDto subjectDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(SubjectDto subjectDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable);

    Page<StudentLessonDto> studentLessonsBy(SubjectDto subjectDto, StudentDto studentDto, LocalDate from, LocalDate to, Pageable pageable);

}
