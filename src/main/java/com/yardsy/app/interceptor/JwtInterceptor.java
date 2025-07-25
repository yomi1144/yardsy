package com.yardsy.app.interceptor;

import com.yardsy.app.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    JwtService jwtService;

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Auth header");
            return false;
        }

        String token = authHeader.substring(7);
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1MiIsImlhdCI6MTc1MzM2MTYyNCwiZXhwIjoxNzUzMzk3NjI0fQ.4rVDGLVQIxIdsZJ9MryFXcAa1iaJT9N4gHyhd4vQyvc

        if (!jwtService.isTokenValid(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or Expired token");
            return false;
        }

        request.setAttribute("userId", jwtService.getUserId(token));
        return true;

    }
}
