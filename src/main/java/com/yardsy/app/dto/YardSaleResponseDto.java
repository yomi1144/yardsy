package com.yardsy.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YardSaleResponseDto {
    private Long id;
    private String title;
    private String description;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private List<ItemResponseDto> items;
} 