package com.yardsy.app.repository;

import com.yardsy.app.model.YardSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YardSaleRepository extends JpaRepository<YardSale, Long> {
    List<YardSale> findAllByUserId(Long userId);
}
