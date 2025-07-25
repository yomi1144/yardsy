package com.yardsy.app.controller;

import com.yardsy.app.dto.ProfileRequestDto;
import com.yardsy.app.dto.ProfileResponseDto;
import com.yardsy.app.service.IProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/profile")
public class ProfileController {

    @Autowired
    private IProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileResponseDto> createProfile(HttpServletRequest request, @Valid @RequestBody ProfileRequestDto profileRequestDto) {
        Long userId = (Long) request.getAttribute("userId");
        ProfileResponseDto profileResponseDto = profileService.createProfile(userId, profileRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileResponseDto);
    }

    @GetMapping
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long userId) {
        ProfileResponseDto profileResponseDto = profileService.getProfileByUserId(userId);

        return ResponseEntity.ok().body(profileResponseDto);
    }

    @PutMapping
    public ResponseEntity<ProfileResponseDto> updateProfile(HttpServletRequest request, @Valid @RequestBody ProfileRequestDto profileRequestDto) {
        Long userId = (Long) request.getAttribute("userId");
        ProfileResponseDto profileResponseDto = profileService.updateProfile(userId, profileRequestDto);

        return ResponseEntity.accepted().body(profileResponseDto);
    }
}
