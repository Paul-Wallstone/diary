package by.school.diary.service.impl;

import by.school.diary.entity.UserEntity;
import by.school.diary.exception.UserNotLoggedInException;
import by.school.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotLoggedInException {
        UserEntity applicationUser = Optional.ofNullable(userRepo.findByUserName(username)).orElseThrow(() -> new UserNotLoggedInException(username));
        return User.builder()
                .username(applicationUser.getUserName())
                .password(applicationUser.getPassword())
                .disabled(!applicationUser.isVerified())
                .accountExpired(applicationUser.isExpired())
                .accountLocked(applicationUser.isLocked())
                .authorities(applicationUser.getRole().name())
                .build();
    }
}
