package by.school.diary.utils;

import by.school.diary.dto.request.UserRequestDto;
import by.school.diary.dto.request.ContactRequestDto;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.dto.response.ContactResponseDto;
import by.school.diary.dto.response.UserResponseDto;
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

    public UserRequestDto toDto(UserResponseDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserRequestDto.class) : null;
    }

    public UserResponseDto toDto(UserEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, UserResponseDto.class) : null;
    }
    

    public ContactEntity toEntity(ContactRequestDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, ContactEntity.class) : null;
    }

    public ContactEntity toEntity(ContactResponseDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, ContactEntity.class) : null;
    }

    public ContactRequestDto toDto(ContactResponseDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, ContactRequestDto.class) : null;
    }

    public ContactResponseDto toDto(ContactEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, ContactResponseDto.class) : null;
    }
}
