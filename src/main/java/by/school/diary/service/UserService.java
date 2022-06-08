package by.school.diary.service;

import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.dto.UserDto;
import by.school.diary.entity.UserEntity;

import java.security.Principal;

public interface UserService extends CRUDService<UserDto, UserDto> {

    UserDto updateCurrentByPrincipal(UserDto userDto, Principal principal);

    void registerUser(SignUpRequestDto signUpRequestDto);

    UserEntity updateUserByPrincipal(SignUpRequestDto signUpRequestDto, Principal principal);

    UserDto getCurrentUser(Principal principal);
}
