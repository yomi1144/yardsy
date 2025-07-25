package com.yardsy.app.service;

import com.yardsy.app.dto.YardSaleRequestDto;
import com.yardsy.app.dto.YardSaleResponseDto;
import java.util.List;

public interface IYardSaleService {
    YardSaleResponseDto createYardSale(Long userId, YardSaleRequestDto yardSaleRequestDto);
    List<YardSaleResponseDto> getAllYardSalesByUserId(Long userId);
    List<YardSaleResponseDto> getAllYardSales();
    YardSaleResponseDto getYardSaleById(Long yardSaleId);
    YardSaleResponseDto updateYardSale(Long userId, Long yardSaleId, YardSaleRequestDto yardSaleRequestDto);
    void deleteYardSale(Long userId, Long yardSaleId);
    YardSaleResponseDto addItemToYardSale(Long userId, Long yardSaleId, Long itemId);
    YardSaleResponseDto removeItemFromYardSale(Long userId, Long yardSaleId, Long itemId);
}
