package by.school.diary.service;

import by.school.diary.dto.RequestUserDto;
import by.school.diary.dto.ResponseUserDto;

import java.util.List;

public interface UserService {
    ResponseUserDto getById(Long id);

    List<ResponseUserDto> getAll();

    ResponseUserDto save(RequestUserDto userDto);

    ResponseUserDto update(RequestUserDto userDto,Long id);

    void deleteById(Long id);
}
