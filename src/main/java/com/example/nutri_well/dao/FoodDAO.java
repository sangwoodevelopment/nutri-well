package com.example.nutri_well.dao;

import com.example.nutri_well.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodDAO {
    Page<Food> searchByFoodName(String name, Pageable pageable);

    Page<Food> searchByCategoryId(Long categoryid, Pageable pageable);

    int getTotalPages(Page<Food> page);

    Food findByName(String name);

    Page<Food> findAllByNutrientsNotIn(@Param("name") String foodname, @Param("names") List<String> names, Pageable pageable);

    Page<Food> findAllByNutrientsNotIn(@Param("category") Long categoryid, @Param("names") List<String> names, Pageable pageable);

    Food findById(Long foodId);
    Food findByFoodCode(String foodcode);
}
