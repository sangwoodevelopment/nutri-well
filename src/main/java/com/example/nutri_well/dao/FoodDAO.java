package com.example.nutri_well.dao;

import com.example.nutri_well.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodDAO {
    Page<Food> searchByFoodName(String name, Pageable pageable);
    Page<Food> searchByCategoryId(Long categoryid, Pageable pageable);
    int getTotalPages(Page<Food> page);
    Food findByName(String name);
}
