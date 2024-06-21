package com.example.nutri_well.service;

import com.example.nutri_well.dto.CategoryResponseDTO;
import com.example.nutri_well.dto.FoodResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {
    List<FoodResponseDTO> searchByFoodName(String name, Pageable pageable);
    List<FoodResponseDTO> searchByCategoryId(CategoryResponseDTO category, Pageable pageable);
    int getTotalPages();
    FoodResponseDTO findByName(String name);
}
