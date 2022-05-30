package by.school.diary.service;

import by.school.diary.dto.request.UserRequestDto;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.dto.response.UserResponseDto;
import by.school.diary.entity.UserEntity;

import java.security.Principal;
import java.util.List;

public interface UserService {
    UserResponseDto getById(Long id);

    List<UserResponseDto> getAll();

    UserResponseDto save(UserRequestDto userDto);

    UserResponseDto update(UserRequestDto userDto, Long id);

    void deleteById(Long id);

    void registerUser(SignUpRequestDto signUpRequestDto);

    UserEntity updateUserByPrincipal(UserRequestDto userRequestDto, Principal principal);

    UserResponseDto getCurrentUser(Principal principal);
}
