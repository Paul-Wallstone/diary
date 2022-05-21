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

    public Optional<UserEntity> toEntity(RequestUserDto dto) {
        return Optional.ofNullable(dto).isPresent() ? Optional.of(mapper.map(dto, UserEntity.class)) : Optional.empty();
    }

    public Optional<UserEntity> toEntity(ResponseUserDto dto) {
        return Optional.ofNullable(dto).isPresent() ? Optional.of(mapper.map(dto, UserEntity.class)) : Optional.empty();
    }

    public ResponseUserDto toDto(UserEntity entity) {
        return Optional.ofNullable(entity).isPresent() ? mapper.map(entity, ResponseUserDto.class) : null;
    }
}
