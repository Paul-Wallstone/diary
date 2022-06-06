package by.school.diary.utils;

import by.school.diary.dto.request.LessonRequestDto;
import by.school.diary.dto.request.UserRequestDto;
import by.school.diary.dto.request.ContactDto;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.dto.response.LessonResponseDto;
import by.school.diary.dto.response.UserResponseDto;
import by.school.diary.entity.StudentLessonEntity;
import by.school.diary.entity.ContactEntity;
import by.school.diary.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomModelMapper {

    @Autowired
    private ModelMapper mapper;

    public UserEntity toEntity(UserRequestDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserEntity.class) : null;
    }

    public UserEntity toEntity(SignUpRequestDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserEntity.class) : null;
    }

    public UserEntity toEntity(UserResponseDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserEntity.class) : null;
    }

    public StudentLessonEntity toEntity(LessonResponseDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, StudentLessonEntity.class) : null;
    }

    public StudentLessonEntity toEntity(LessonRequestDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, StudentLessonEntity.class) : null;
    }

    public UserRequestDto toDto(UserResponseDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserRequestDto.class) : null;
    }

    public UserResponseDto toDto(UserEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, UserResponseDto.class) : null;
    }

    public LessonResponseDto toDto(StudentLessonEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, LessonResponseDto.class) : null;
    }

    public LessonRequestDto toDto(LessonResponseDto entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, LessonRequestDto.class) : null;
    }

    public ContactEntity toEntity(ContactDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, ContactEntity.class) : null;
    }

    public ContactDto toDto(ContactEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, ContactDto.class) : null;
    }
}
