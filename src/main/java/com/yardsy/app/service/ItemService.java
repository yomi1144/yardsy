package com.yardsy.app.service;

import com.yardsy.app.dto.ItemRequestDto;
import com.yardsy.app.dto.ItemResponseDto;
import com.yardsy.app.exception.ResourceNotFoundException;
import com.yardsy.app.model.Item;
import com.yardsy.app.model.User;
import com.yardsy.app.repository.ItemRepository;
import com.yardsy.app.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ItemResponseDto createItem(ItemRequestDto itemRequestDto) {
        User user = userRepository.findById(itemRequestDto.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", itemRequestDto.getUserId()));

        Item item = modelMapper.map(itemRequestDto, Item.class);
        item.setUser(user);

        Item savedItem = itemRepository.save(item);

        return modelMapper.map(savedItem, ItemResponseDto.class);
    }

    @Override
    public List<ItemResponseDto> getAllItemsByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", userId));

        List<Item> items = itemRepository.findAllByUserId(userId);

        return items.stream().map(item -> modelMapper.map(item, ItemResponseDto.class)).toList();
    }

    @Override
    public ItemResponseDto getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("item", "id", itemId));

        return modelMapper.map(item, ItemResponseDto.class);
    }

    @Override
    public ItemResponseDto updateItem(Long itemId, ItemRequestDto itemRequestDto) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("item", "id", itemId));
        userRepository.findById(itemRequestDto.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", itemRequestDto.getUserId()));

        if (item.getUser().getId() != itemRequestDto.getUserId()) {
            throw new RuntimeException("The yard sale was created by someone else. Cant update.");
        }

        modelMapper.map(itemRequestDto, item);

        Item updatedItem = itemRepository.save(item);

        return modelMapper.map(updatedItem, ItemResponseDto.class);
    }

    @Override
    public void deleteItem(Long itemId, Long userId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("item", "id", itemId));
        userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "ID", userId));
        if (item.getUser().getId() != userId) {
            throw new RuntimeException("The item was created by someone else. Cant Delete.");
        }
        itemRepository.delete(item);
    }
}
