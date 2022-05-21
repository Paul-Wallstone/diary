package by.school.diary.service;

import by.school.diary.dto.ResponseUserDto;

import java.util.List;

public interface UserService {
    ResponseUserDto getUserById(Long id);
    List<ResponseUserDto> getAllUsers();
}
