package com.yardsy.app.controller;

import com.yardsy.app.dto.ItemRequestDto;
import com.yardsy.app.dto.ItemResponseDto;
import com.yardsy.app.service.IItemService;
import com.yardsy.app.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    IItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(HttpServletRequest request, @Valid @RequestBody ItemRequestDto itemRequestDto) {
        Long userId = (Long) request.getAttribute("userId");
        ItemResponseDto responseDto = itemService.createItem(userId, itemRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ItemResponseDto>> getAllItemsByUserId(@PathVariable Long userId) {
        List<ItemResponseDto> responseDtos = itemService.getAllItemsByUserId(userId);

        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDto> getItemById(@PathVariable Long itemId) {
        ItemResponseDto item = itemService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemResponseDto> updateItem(HttpServletRequest request, @PathVariable Long itemId, @Valid @RequestBody ItemRequestDto itemRequestDto) {
        Long userId = (Long) request.getAttribute("userId");
        ItemResponseDto updatedItem = itemService.updateItem(userId, itemId, itemRequestDto);
        return ResponseEntity.accepted().body(updatedItem);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(HttpServletRequest request, @PathVariable Long itemId) {
        Long userId = (Long) request.getAttribute("userId");
        itemService.deleteItem(itemId, userId);
        return ResponseEntity.noContent().build();
    }
}
