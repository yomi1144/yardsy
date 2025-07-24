package com.yardsy.app.repository;

import com.yardsy.app.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUserId(Long userId);
}
