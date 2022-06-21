package by.school.diary.service;

import by.school.diary.dto.ParentDto;
import by.school.diary.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//TODO реализовать интерфейс и DTO +добавить свои методы
public interface ParentService extends CRUDService<ParentDto, ParentDto> {

    void delete(ParentDto parentDto);

    Page<StudentDto> allStudentsBy(ParentDto parentDto, Pageable pageable);
}
