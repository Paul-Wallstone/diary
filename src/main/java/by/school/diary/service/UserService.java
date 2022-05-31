package by.school.diary.service;

import by.school.diary.dto.request.UserRequestDto;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto getById(Long id);

    List<UserResponseDto> getAll();

    UserResponseDto save(UserRequestDto userDto);

    UserResponseDto update(UserRequestDto userDto, Long id);

    void deleteById(Long id);

    void registerUser(SignUpRequestDto signUpRequestDto);
}
