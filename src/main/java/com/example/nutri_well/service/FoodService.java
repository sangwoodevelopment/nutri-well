package com.example.nutri_well.service;

import com.example.nutri_well.dto.CategoryResponseDTO;
import com.example.nutri_well.dto.FoodResponseDTO;
import com.example.nutri_well.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodService {
    List<FoodResponseDTO> searchByFoodName(String name, Pageable pageable);
    List<FoodResponseDTO> searchByCategoryId(CategoryResponseDTO category, Pageable pageable);
    int getTotalPages();
    FoodResponseDTO findByName(String name);
    List<FoodResponseDTO> findAllByNutrientsNotIn(@Param("name")String foodname, @Param("names") List<String> names, Pageable pageable);
    List<FoodResponseDTO> findAllByNutrientsNotIn(@Param("category")CategoryResponseDTO category, @Param("names") List<String> names, Pageable pageable);
}
