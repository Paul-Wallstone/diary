package by.school.diary.service.impl;

import by.school.diary.entity.UserEntity;
import by.school.diary.exception.UserNotLoggedInException;
import by.school.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = Optional.ofNullable(userRepo.findByUsername(username)).orElseThrow(() -> new UserNotLoggedInException(username));
        return build(user);
    }

    public UserEntity loadUserById(Long id) {
        return userRepo.findById(id).orElse(null);

    }

    public static UserEntity build(UserEntity user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        return new UserEntity(
                user.getId(),
                user.getPassword(),
                user.getUsername(),
                user.getEmail(),
                authorities
        );
    }

}
