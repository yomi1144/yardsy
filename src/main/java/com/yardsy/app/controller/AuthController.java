package com.yardsy.app.controller;

import com.yardsy.app.dto.AuthRequestDto;
import com.yardsy.app.dto.AuthResponseDto;
import com.yardsy.app.model.User;
import com.yardsy.app.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@Valid @RequestBody AuthRequestDto requestDto) {
       AuthResponseDto responseDto = authService.signup(requestDto);

       return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto requestDto) {
        AuthResponseDto responseDto = authService.login(requestDto);

        return ResponseEntity.ok().body(responseDto);
    }
}
