package com.example.nutri_well.dto;

import com.example.nutri_well.entity.FoodNutrientApprove;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodNutrientApproveDTO {
    private Long nutrientId;
    private double amount;

    public static FoodNutrientApproveDTO of(FoodNutrientApprove foodNutrientApprove) {
        return new FoodNutrientApproveDTO(
                foodNutrientApprove.getNutrient().getId(),
                foodNutrientApprove.getAmount()
        );
    }
}
