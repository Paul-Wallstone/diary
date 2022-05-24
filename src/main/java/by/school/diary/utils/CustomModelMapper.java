package by.school.diary.utils;

import by.school.diary.dto.RequestUserDto;
import by.school.diary.dto.ResponseUserDto;
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

    public UserEntity toEntity(ResponseUserDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, UserEntity.class) : null;
    }

    public RequestUserDto toDto(ResponseUserDto dto) {
        return Optional.ofNullable(dto).isPresent() ? mapper.map(dto, RequestUserDto.class) : null;
    }

    public ResponseUserDto toDto(UserEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, ResponseUserDto.class) : null;
    }
}
