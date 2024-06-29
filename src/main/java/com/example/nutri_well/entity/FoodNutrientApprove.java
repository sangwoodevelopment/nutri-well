package com.example.nutri_well.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "foodNutrientApprove")
public class FoodNutrientApprove {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "foodApproveId")
    private FoodApprove foodApprove;

    @ManyToOne
    @JoinColumn(name = "nutrientId")
    private Nutrient nutrient;

    private double amount;

    public FoodNutrientApprove(FoodApprove foodApprove, Nutrient nutrient, double amount) {
        this.foodApprove = foodApprove;
        this.nutrient = nutrient;
        this.amount = amount;
    }
}
