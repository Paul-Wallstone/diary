package by.school.diary.service;

import by.school.diary.dto.*;

import java.time.LocalDate;
import java.util.List;


//TODO реализовать интерфейс и DTO + свои методы
public interface SubjectService extends CRUDService<SubjectDto, SubjectDto> {

    void delete(SubjectDto subjectDto);

    List<LessonDto> lessonsBy(SubjectDto subjectDto);

    List<LessonDto> lessonsBy(SubjectDto subjectDto, LocalDate from, LocalDate to);

    List<LessonDto> lessonsBy(SubjectDto subjectDto, GroupDto groupDto, LocalDate from, LocalDate to);

    List<LessonDto> lessonsBy(SubjectDto subjectDto, StudentDto studentDto, LocalDate from, LocalDate to);

    List<StudentLessonDto> studentLessonsBy(SubjectDto subjectDto);

    List<StudentLessonDto> studentLessonsBy(SubjectDto subjectDto, LocalDate from, LocalDate to);

    List<StudentLessonDto> studentLessonsBy(SubjectDto subjectDto, GroupDto groupDto, LocalDate from, LocalDate to);

    List<StudentLessonDto> studentLessonsBy(SubjectDto subjectDto, StudentDto studentDto, LocalDate from, LocalDate to);

}
