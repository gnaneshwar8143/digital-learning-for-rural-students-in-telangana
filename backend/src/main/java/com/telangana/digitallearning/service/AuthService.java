package com.telangana.digitallearning.service;

import com.telangana.digitallearning.dto.AuthRequest;
import com.telangana.digitallearning.dto.AuthResponse;
import com.telangana.digitallearning.dto.RegisterRequest;
import com.telangana.digitallearning.entity.Role;
import com.telangana.digitallearning.entity.User;
import com.telangana.digitallearning.exception.BadRequestException;
import com.telangana.digitallearning.repository.UserRepository;
import com.telangana.digitallearning.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("User not found"));

        String token = tokenProvider.generateToken(authentication);

        return AuthResponse.builder()
                .token(token)
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .classLevel(user.getClassLevel())
                .preferredLanguage(user.getPreferredLanguage())
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email address already registered");
        }

        Role userRole = Role.ROLE_STUDENT;
        if ("TEACHER".equalsIgnoreCase(request.getRole()) || "ROLE_TEACHER".equalsIgnoreCase(request.getRole())) {
            userRole = Role.ROLE_TEACHER;
        } else if ("ADMIN".equalsIgnoreCase(request.getRole()) || "ROLE_ADMIN".equalsIgnoreCase(request.getRole())) {
            userRole = Role.ROLE_ADMIN;
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(userRole)
                .classLevel(request.getClassLevel() != null ? request.getClassLevel() : 10)
                .preferredLanguage(request.getPreferredLanguage() != null ? request.getPreferredLanguage() : "en")
                .build();

        User savedUser = userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = tokenProvider.generateToken(authentication);

        return AuthResponse.builder()
                .token(token)
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .classLevel(savedUser.getClassLevel())
                .preferredLanguage(savedUser.getPreferredLanguage())
                .build();
    }
}
