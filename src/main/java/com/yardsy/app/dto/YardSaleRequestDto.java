package com.yardsy.app.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YardSaleRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 1, max = 100)
    private String description;

    @NotBlank
    @Size(min = 1, max = 100)
    private String location;

    @NotNull
    @Future
    private OffsetDateTime startTime;

    @NotNull
    @Future
    private OffsetDateTime endTime;
    
    private Set<Long> itemIds;
} 