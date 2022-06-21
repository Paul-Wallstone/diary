package by.school.diary.service.impl;

import by.school.diary.domain.Role;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.dto.UserDto;
import by.school.diary.entity.UserEntity;
import by.school.diary.exception.NotCurrentUserException;
import by.school.diary.exception.UserExistException;
import by.school.diary.exception.UserNotFoundException;
import by.school.diary.repository.UserRepository;
import by.school.diary.service.UserService;
import by.school.diary.utils.mapper.CustomModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomModelMapper modelMapper;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDto getById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.toDto(userEntity);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> userEntities = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return userEntities.stream().map(user -> modelMapper.toDto(user)).collect(Collectors.toList());
    }


    @Override
    public UserDto update(UserDto dto) {
        return update(dto, dto.getId());
    }

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity user = modelMapper.toEntity(userDto);
        user.getRoles().add(Role.ROLE_USER);
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.toDto(savedUser);
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
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

    @Override
    public void registerUser(SignUpRequestDto signUpRequestDto) {
        UserEntity user = modelMapper.toEntity(signUpRequestDto);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(encoder.encode(signUpRequestDto.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }

    @Override
    public UserEntity updateUserByPrincipal(SignUpRequestDto signUpRequestDto, Principal principal) {
        UserEntity userEntity = getUserByPrincipal(principal);
        userEntity.setUsername(signUpRequestDto.getUsername());

        return userRepository.save(userEntity);
    }

    @Override
    public UserDto getCurrentUser(Principal principal) {
        return modelMapper.toDto(getUserByPrincipal(principal));
    }

    private UserEntity getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username" + username);
        }
    }

    @Override
    public UserDto updateCurrentByPrincipal(UserDto userDto, Principal principal) {
        UserEntity userEntity = getUserByPrincipal(principal);
        if (userEntity.getId().equals(userDto.getId())) {
            userEntity.setUsername(userDto.getUsername());
            userEntity.setInfo(modelMapper.toEntity(userDto.getInfo()));
            userEntity.setContact(modelMapper.toEntity(userDto.getContact()));

            UserEntity user = userRepository.save(userEntity);
            return modelMapper.toDto(user);
        }
        throw new NotCurrentUserException("This is not current user. Please revise your id!");
    }


}
