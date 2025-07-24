package com.yardsy.app.service;

import com.yardsy.app.dto.AuthRequestDto;
import com.yardsy.app.dto.AuthResponseDto;
import com.yardsy.app.model.User;

public interface IAuthService {

    AuthResponseDto signup(AuthRequestDto authRequestDto);
    AuthResponseDto login(AuthRequestDto authRequestDto);
}
