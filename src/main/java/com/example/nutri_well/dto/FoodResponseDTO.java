package com.example.nutri_well.dto;

import com.example.nutri_well.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//njg 순환참조를 방지하기위한 Response용 DTO
@Data
@AllArgsConstructor
public class FoodResponseDTO {
    private Long id;
    private String name;
    private String categoryName;

    private String foodCode;
    private String product;

    private String manufacturer;
    private String servingSize;

    private List<FoodNutrientResponseDTO> nutrientlist = new ArrayList<>();
    //엔티티를 DTO로 변환하는 메소드
    public static FoodResponseDTO of(Food food){
        return new FoodResponseDTO(food.getId(),food.getName(), food.getCategoryId().getName(), food.getFoodCode(),
                food.getProduct(), food.getManufacturer(), food.getServingSize(),
                food.getNutrientlist().stream()
                        .map(FoodNutrientResponseDTO::of)
                        .collect(Collectors.toList()));
    }

}
