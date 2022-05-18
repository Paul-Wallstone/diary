package by.school.diary.service.impl;

import by.school.diary.dto.UserDto;
import by.school.diary.entity.UserEntity;
import by.school.diary.exception.UserNotFoundException;
import by.school.diary.repository.UserRepository;
import by.school.diary.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        Iterable<UserEntity> userEntities = userRepository.findAll();
        return Stream.of(userEntities).map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
}
