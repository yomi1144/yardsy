package com.yardsy.app.controller;

import com.yardsy.app.dto.YardSaleRequestDto;
import com.yardsy.app.dto.YardSaleResponseDto;
import com.yardsy.app.service.IYardSaleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/yardsales")
@Validated
public class YardSaleController {

    @Autowired
    IYardSaleService yardSaleService;

    @PostMapping
    public ResponseEntity<YardSaleResponseDto> createYardSale(HttpServletRequest request, @Valid @RequestBody YardSaleRequestDto yardSaleRequestDto) {
        Long userId = (Long) request.getAttribute("userId");
        YardSaleResponseDto responseDto = yardSaleService.createYardSale(userId, yardSaleRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<YardSaleResponseDto>> getAllYardSales() {
        List<YardSaleResponseDto> yardSales = yardSaleService.getAllYardSales();
        return ResponseEntity.ok(yardSales);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<YardSaleResponseDto>> getAllYardSalesByUserId(@PathVariable Long userId) {
        List<YardSaleResponseDto> yardSales = yardSaleService.getAllYardSalesByUserId(userId);
        return ResponseEntity.ok(yardSales);
    }

    @GetMapping("/{yardSaleId}")
    public ResponseEntity<YardSaleResponseDto> getYardSaleById(@PathVariable Long yardSaleId) {
        YardSaleResponseDto yardSale = yardSaleService.getYardSaleById(yardSaleId);
        return ResponseEntity.ok(yardSale);
    }

    @PutMapping("/{yardSaleId}")
    public ResponseEntity<YardSaleResponseDto> updateYardSale(HttpServletRequest request, @PathVariable Long yardSaleId, @Valid @RequestBody YardSaleRequestDto yardSaleRequestDto) {
        Long userId = (Long) request.getAttribute("userId");
        YardSaleResponseDto updatedYardSale = yardSaleService.updateYardSale(userId, yardSaleId, yardSaleRequestDto);
        return ResponseEntity.accepted().body(updatedYardSale);
    }

    @DeleteMapping("/{yardSaleId}")
    public ResponseEntity<Void> deleteYardSale(HttpServletRequest request, @PathVariable Long yardSaleId) {
        Long userId = (Long) request.getAttribute("userId");
        yardSaleService.deleteYardSale(userId, yardSaleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{yardSaleId}/items/{itemId}")
    public ResponseEntity<YardSaleResponseDto> addItemToYardSale(HttpServletRequest request, @PathVariable Long yardSaleId, @PathVariable Long itemId) {
        Long userId = (Long) request.getAttribute("userId");
        YardSaleResponseDto updatedYardSale = yardSaleService.addItemToYardSale(userId, yardSaleId, itemId);
        return ResponseEntity.ok(updatedYardSale);
    }

    @DeleteMapping("/{yardSaleId}/items/{itemId}")
    public ResponseEntity<YardSaleResponseDto> removeItemFromYardSale(HttpServletRequest request, @PathVariable Long yardSaleId, @PathVariable Long itemId) {
        Long userId = (Long) request.getAttribute("userId");
        YardSaleResponseDto updatedYardSale = yardSaleService.removeItemFromYardSale(userId, yardSaleId, itemId);
        return ResponseEntity.ok(updatedYardSale);
    }
}
