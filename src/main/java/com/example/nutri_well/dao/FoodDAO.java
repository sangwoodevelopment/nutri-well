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
    @Query("SELECT f FROM Food f " +
            "WHERE f.name LIKE :name AND " +
            "NOT EXISTS (SELECT 1 FROM f.nutrientlist fn WHERE fn.nutrient.name IN :names)")
    Page<Food> findAllByNutrientsNotIn(@Param("name")String foodname, @Param("names") List<String> names, Pageable pageable);
}
