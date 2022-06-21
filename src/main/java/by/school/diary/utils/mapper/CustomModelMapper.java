package by.school.diary.utils.mapper;

import by.school.diary.dto.InfoDto;
import by.school.diary.dto.LessonDto;
import by.school.diary.dto.StudentDto;
import by.school.diary.dto.StudentLessonDto;
import by.school.diary.dto.ContactDto;
import by.school.diary.dto.EmployeeDto;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.dto.UserDto;
import by.school.diary.entity.InfoEntity;
import by.school.diary.entity.LessonEntity;
import by.school.diary.entity.StudentEntity;
import by.school.diary.entity.StudentLessonEntity;
import by.school.diary.entity.ContactEntity;
import by.school.diary.entity.EmployeeEntity;
import by.school.diary.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomModelMapper {


    private ModelMapper mapper;

    @Autowired
    public CustomModelMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserEntity toEntity(SignUpRequestDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, UserEntity.class)
                : null;
    }

    public UserEntity toEntity(UserDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, UserEntity.class)
                : null;
    }

    public LessonEntity toEntity(LessonDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, LessonEntity.class)
                : null;
    }

    public UserDto toDto(UserEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, UserDto.class)
                : null;
    }

    public LessonDto toDto(LessonEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, LessonDto.class)
                : null;
    }

    public ContactEntity toEntity(ContactDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, ContactEntity.class)
                : null;
    }

    public ContactDto toDto(ContactEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, ContactDto.class)
                : null;
    }

    public InfoEntity toEntity(InfoDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, InfoEntity.class)
                : null;
    }

    public InfoDto toDto(InfoEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, InfoDto.class)
                : null;
    }

    public StudentLessonEntity toEntity(StudentLessonDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, StudentLessonEntity.class)
                : null;
    }

    public StudentLessonDto toDto(StudentLessonEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, StudentLessonDto.class)
                : null;
    }

    public StudentEntity toEntity(StudentDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, StudentEntity.class)
                : null;
    }

    public StudentDto toDto(StudentEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, StudentDto.class)
                : null;
    }

    public EmployeeEntity toEntity(EmployeeDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, EmployeeEntity.class)
                : null;
    }

    public EmployeeDto toDto(EmployeeEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, EmployeeDto.class)
                : null;
    }
}
