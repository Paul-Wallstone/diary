package by.school.diary.service;

import by.school.diary.entity.AppUser;
import by.school.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> applicationUser = Optional.ofNullable(userRepo.findByUsername(username));
        return User.builder()
                .username(applicationUser.get().getUsername())
                .password(applicationUser.get().getPassword())
                .disabled(!applicationUser.get().isVerified())
                .accountExpired(applicationUser.get().isExpired())
                .accountLocked(applicationUser.get().isLocked())
                .authorities(applicationUser.get().getRole().name())
                .build();
    }
}
