package com.yardsy.app.service;

import com.yardsy.app.dto.AuthRequestDto;
import com.yardsy.app.dto.AuthResponseDto;
import com.yardsy.app.exception.ResourceNotFoundException;
import com.yardsy.app.model.User;
import com.yardsy.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Override
    public AuthResponseDto signup(AuthRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RuntimeException("There is already a user with email " + requestDto.getEmail());
        }

        String hashedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = User.builder()
                .email(requestDto.getEmail())
                .passwordHash(hashedPassword)
                .build();

        userRepository.save(user);

        return AuthResponseDto.builder().message("User registered Successfully. Please login.").success(true).build();
    }

    @Override
    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        User user = userRepository.findByEmail(authRequestDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "Email", authRequestDto.getEmail()));

        if (!passwordEncoder.matches(authRequestDto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid Email or Password.");
        }

        String token = jwtService.generateToken(user.getId());

        return AuthResponseDto.builder()
                .success(true)
                .message("Login Successful")
                .id(user.getId())
                .email(user.getEmail())
                .token(token)
                .build();
    }
}
