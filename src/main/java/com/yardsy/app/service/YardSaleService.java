package com.yardsy.app.service;

import com.yardsy.app.dto.YardSaleRequestDto;
import com.yardsy.app.dto.YardSaleResponseDto;
import com.yardsy.app.exception.ResourceNotFoundException;
import com.yardsy.app.model.Item;
import com.yardsy.app.model.User;
import com.yardsy.app.model.YardSale;
import com.yardsy.app.repository.ItemRepository;
import com.yardsy.app.repository.UserRepository;
import com.yardsy.app.repository.YardSaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YardSaleService implements IYardSaleService{
    @Autowired
    YardSaleRepository yardSaleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public YardSaleResponseDto createYardSale(YardSaleRequestDto yardSaleRequestDto) {
        User user = userRepository.findById(yardSaleRequestDto.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", yardSaleRequestDto.getUserId()));

        YardSale yardSale = modelMapper.map(yardSaleRequestDto, YardSale.class);
        yardSale.setUser(user);

        if (yardSaleRequestDto.getItemIds() != null && !yardSaleRequestDto.getItemIds().isEmpty()) {
            List<Item> items = itemRepository.findAllById(yardSaleRequestDto.getItemIds());
            yardSale.setItems(items);
        }

        YardSale savedYardSale = yardSaleRepository.save(yardSale);
        return modelMapper.map(savedYardSale, YardSaleResponseDto.class);
    }

    @Override
    public List<YardSaleResponseDto> getAllYardSalesByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", userId));

        List<YardSale> yardSales = yardSaleRepository.findAllByUserId(userId);

        return yardSales.stream().map(yardSale -> modelMapper.map(yardSale, YardSaleResponseDto.class)).toList();
    }

    @Override
    public List<YardSaleResponseDto> getAllYardSales() {
        List<YardSale> yardSales = yardSaleRepository.findAll();
        return yardSales.stream().map(yardSale -> modelMapper.map(yardSale, YardSaleResponseDto.class)).toList();
    }

    @Override
    public YardSaleResponseDto getYardSaleById(Long yardSaleId) {
        YardSale yardSale = yardSaleRepository.findById(yardSaleId).orElseThrow(() -> new ResourceNotFoundException("YardSale", "id", yardSaleId));

        return modelMapper.map(yardSale, YardSaleResponseDto.class);
    }

    @Override
    public YardSaleResponseDto updateYardSale(Long yardSaleId, YardSaleRequestDto yardSaleRequestDto) {
        YardSale yardSale = yardSaleRepository.findById(yardSaleId).orElseThrow(() -> new ResourceNotFoundException("YardSale", "id", yardSaleId));
        userRepository.findById(yardSaleRequestDto.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", yardSaleRequestDto.getUserId()));

        if (yardSale.getUser().getId() != yardSaleRequestDto.getUserId()) {
            throw new RuntimeException("The yard sale was created by someone else. Cant update.");
        }

        modelMapper.map(yardSaleRequestDto, yardSale);

        YardSale updatedYardSale = yardSaleRepository.save(yardSale);

        return modelMapper.map(updatedYardSale, YardSaleResponseDto.class);
    }

    @Override
    public void deleteYardSale(Long userId, Long yardSaleId) {
        YardSale yardSale = yardSaleRepository.findById(yardSaleId).orElseThrow(() -> new ResourceNotFoundException("YardSale", "id", yardSaleId));
        userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", userId));

        if (!yardSale.getUser().getId().equals(userId)) {
            throw new RuntimeException("The yard sale was created by someone else. Cant Delete.");
        }

        yardSaleRepository.save(yardSale);
    }

    @Override
    public YardSaleResponseDto addItemToYardSale(Long userId, Long yardSaleId, Long itemId) {
        YardSale yardSale = yardSaleRepository.findById(yardSaleId).orElseThrow(() -> new ResourceNotFoundException("YardSale", "id", yardSaleId));
        userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", userId));
        if (!yardSale.getUser().getId().equals(userId)) {
            throw new RuntimeException("The yard sale was created by someone else. Cant Add Item.");
        }

        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("item", "id", itemId));

        yardSale.getItems().add(item);

        YardSale updatedYardSale = yardSaleRepository.save(yardSale);

        return modelMapper.map(updatedYardSale, YardSaleResponseDto.class);
    }

    @Override
    public YardSaleResponseDto removeItemFromYardSale(Long userId, Long yardSaleId, Long itemId) {
        YardSale yardSale = yardSaleRepository.findById(yardSaleId).orElseThrow(() -> new ResourceNotFoundException("YardSale", "id", yardSaleId));
        userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", userId));
        if (!yardSale.getUser().getId().equals(userId)) {
            throw new RuntimeException("The yard sale was created by someone else. Cant Add Item.");
        }
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("item", "id", itemId));

        yardSale.getItems().remove(item);

        YardSale updatedYardSale = yardSaleRepository.save(yardSale);

        return modelMapper.map(updatedYardSale, YardSaleResponseDto.class);
    }
}
