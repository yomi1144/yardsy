package com.yardsy.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String bio;
    private String street;
    private String city;
    private String state;
    private String country;
}
