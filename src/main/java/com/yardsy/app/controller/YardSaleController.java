package com.yardsy.app.controller;

import com.yardsy.app.dto.YardSaleRequestDto;
import com.yardsy.app.dto.YardSaleResponseDto;
import com.yardsy.app.service.IYardSaleService;
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
    public ResponseEntity<YardSaleResponseDto> createYardSale(@Valid @RequestBody YardSaleRequestDto yardSaleRequestDto) {
        YardSaleResponseDto responseDto = yardSaleService.createYardSale(yardSaleRequestDto);

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
    public ResponseEntity<YardSaleResponseDto> updateYardSale(@PathVariable Long yardSaleId, @Valid @RequestBody YardSaleRequestDto yardSaleRequestDto) {
        YardSaleResponseDto updatedYardSale = yardSaleService.updateYardSale(yardSaleId, yardSaleRequestDto);
        return ResponseEntity.accepted().body(updatedYardSale);
    }

    @DeleteMapping("/{yardSaleId}")
    public ResponseEntity<Void> deleteYardSale(@RequestParam Long userId, @PathVariable Long yardSaleId) {
        yardSaleService.deleteYardSale(userId, yardSaleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{yardSaleId}/items/{itemId}")
    public ResponseEntity<YardSaleResponseDto> addItemToYardSale(@RequestParam Long userId, @PathVariable Long yardSaleId, @PathVariable Long itemId) {
        YardSaleResponseDto updatedYardSale = yardSaleService.addItemToYardSale(userId, yardSaleId, itemId);
        return ResponseEntity.ok(updatedYardSale);
    }

    @DeleteMapping("/{yardSaleId}/items/{itemId}")
    public ResponseEntity<YardSaleResponseDto> removeItemFromYardSale(@RequestParam Long userId, @PathVariable Long yardSaleId, @PathVariable Long itemId) {
        YardSaleResponseDto updatedYardSale = yardSaleService.removeItemFromYardSale(userId, yardSaleId, itemId);
        return ResponseEntity.ok(updatedYardSale);
    }
}
