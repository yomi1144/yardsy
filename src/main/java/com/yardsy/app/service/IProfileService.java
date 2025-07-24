package com.yardsy.app.service;

import com.yardsy.app.dto.ProfileRequestDto;
import com.yardsy.app.dto.ProfileResponseDto;

public interface IProfileService {
    ProfileResponseDto createProfile(Long userId, ProfileRequestDto profileRequestDto);
    ProfileResponseDto getProfileByUserId(Long userId);
    ProfileResponseDto updateProfile(Long userId, ProfileRequestDto profileRequestDto);
}
