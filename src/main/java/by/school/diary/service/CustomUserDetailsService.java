package by.school.diary.service;

import by.school.diary.entity.AppUser;
import by.school.diary.exception.UserNotLoggedInException;
import by.school.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotLoggedInException {
        AppUser applicationUser = Optional.ofNullable(userRepo.findByUsername(username)).orElseThrow(() -> new UserNotLoggedInException(username));
        return User.builder()
                .username(applicationUser.getUsername())
                .password(applicationUser.getPassword())
                .disabled(!applicationUser.isVerified())
                .accountExpired(applicationUser.isExpired())
                .accountLocked(applicationUser.isLocked())
                .authorities(applicationUser.getRole().name())
                .build();
    }
}
