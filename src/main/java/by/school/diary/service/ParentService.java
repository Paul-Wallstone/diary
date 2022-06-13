package by.school.diary.service;

import by.school.diary.dto.ParentDto;
import by.school.diary.dto.StudentDto;

import java.util.List;

//TODO реализовать интерфейс и DTO +добавить свои методы
public interface ParentService extends CRUDService<ParentDto, ParentDto> {

    void delete(ParentDto parentDto);

    List<StudentDto> allStudentsBy(ParentDto parentDto);
}
