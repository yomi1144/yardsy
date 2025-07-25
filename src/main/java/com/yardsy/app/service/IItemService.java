package com.yardsy.app.service;

import com.yardsy.app.dto.ItemRequestDto;
import com.yardsy.app.dto.ItemResponseDto;

import java.util.List;

public interface IItemService {

    ItemResponseDto createItem(Long userId, ItemRequestDto itemRequestDto);
    List<ItemResponseDto> getAllItemsByUserId(Long userId);

    ItemResponseDto getItemById(Long itemId);
    ItemResponseDto updateItem(Long userId, Long itemId, ItemRequestDto itemRequestDto);
    void deleteItem(Long itemId, Long userId);
}
