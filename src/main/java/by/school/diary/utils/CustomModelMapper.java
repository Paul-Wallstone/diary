package by.school.diary.utils;

import by.school.diary.dto.InfoDto;
import by.school.diary.dto.request.LessonRequestDto;
import by.school.diary.dto.ContactDto;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.dto.response.LessonResponseDto;
import by.school.diary.dto.UserDto;
import by.school.diary.entity.InfoEntity;
import by.school.diary.entity.LessonEntity;
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


    public UserEntity toEntity(SignUpRequestDto dto) {
        this.mapper.createTypeMap(dto, UserEntity.class)
                .addMappings(mapper -> mapper.map(src -> src, UserEntity::setInfo));

        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, UserEntity.class)
                : null;
    }

    public UserEntity toEntity(UserDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserEntity.class) : null;
    }

    public LessonEntity toEntity(LessonResponseDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, LessonEntity.class) : null;
    }

    public LessonEntity toEntity(LessonRequestDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, LessonEntity.class) : null;
    }

    public UserDto toDto(UserEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, UserDto.class) : null;
    }

    public LessonResponseDto toDto(LessonEntity entity) {
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

    public InfoEntity toEntity(InfoDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, InfoEntity.class) : null;
    }

    public InfoDto toDto(InfoEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, InfoDto.class) : null;
    }
}
