package by.school.diary.utils;

import by.school.diary.dto.request.RequestUserDto;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.dto.response.UserResponseDto;
import by.school.diary.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomModelMapper {

    @Autowired
    private ModelMapper mapper;

    public UserEntity toEntity(RequestUserDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserEntity.class) : null;
    }

    public UserEntity toEntity(SignUpRequestDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserEntity.class) : null;
    }

    public UserEntity toEntity(UserResponseDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserEntity.class) : null;
    }

    public RequestUserDto toDto(UserResponseDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, RequestUserDto.class) : null;
    }

    public UserResponseDto toDto(UserEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, UserResponseDto.class) : null;
    }
}
