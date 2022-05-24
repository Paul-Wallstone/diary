package by.school.diary.service.impl;

import by.school.diary.domain.Role;
import by.school.diary.dto.RequestUserDto;
import by.school.diary.dto.ResponseUserDto;
import by.school.diary.entity.UserEntity;
import by.school.diary.exception.UserNotFoundException;
import by.school.diary.repository.UserRepository;
import by.school.diary.service.UserService;
import by.school.diary.utils.CustomModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder encoder;

    @Override
    public ResponseUserDto getById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.toDto(userEntity);
    }

    @Override
    public List<ResponseUserDto> getAll() {
        List<UserEntity> userEntities = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return userEntities.stream().map(user -> modelMapper.toDto(user)).collect(Collectors.toList());
    }

    @Override
    public ResponseUserDto save(RequestUserDto userDto) {
        UserEntity user = modelMapper.toEntity(userDto);
        user.setRole(Role.ROLE_GUEST);
        user.setPassword(encoder.encode(userDto.getPassword()));
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.toDto(savedUser);
    }

    @Override
    public ResponseUserDto update(RequestUserDto userDto, Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userEntity.setPassword(encoder.encode(userDto.getPassword()));
        userEntity.setUserName(userDto.getUserName());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        UserEntity updatedUser = userRepository.save(userEntity);
        return modelMapper.toDto(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
