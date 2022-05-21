package by.school.diary.service.impl;

import by.school.diary.dto.ResponseUserDto;
import by.school.diary.entity.UserEntity;
import by.school.diary.exception.UserNotFoundException;
import by.school.diary.repository.UserRepository;
import by.school.diary.service.UserService;
import by.school.diary.utils.CustomModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomModelMapper modelMapper;

    @Override
    public ResponseUserDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.toDto(userEntity);
    }

    @Override
    public List<ResponseUserDto> getAllUsers() {
        List<UserEntity> userEntities = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return userEntities.stream().map(user -> modelMapper.toDto(user)).collect(Collectors.toList());
    }
}
