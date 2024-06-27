package com.example.nutri_well.dto;

import com.example.nutri_well.entity.Category;
import com.example.nutri_well.entity.FoodApprove;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodApproveResponseDTO {
    private Long id;
    private String name;
    private Category categoryId;
    private String product;
    private Date requestDate;
    private Date approvalDate;
    private String manufacturer;
    private String servingSize;
    private String userEmail;
    private boolean approved;
    private List<FoodNutrientApproveDTO> nutrients;

    public static FoodApproveResponseDTO of(FoodApprove foodApprove) {
        return new FoodApproveResponseDTO(
                foodApprove.getId(),
                foodApprove.getName(),
                foodApprove.getCategoryId(),
                foodApprove.getProduct(),
                foodApprove.getRequestDate(),
                foodApprove.getApprovalDate(),
                foodApprove.getManufacturer(),
                foodApprove.getServingSize(),
                foodApprove.getUser().getEmail(),
                foodApprove.isApproved(),
                foodApprove.getNutrientlist().stream()
                        .map(FoodNutrientApproveDTO::of)
                        .collect(Collectors.toList())
        );
    }
}
