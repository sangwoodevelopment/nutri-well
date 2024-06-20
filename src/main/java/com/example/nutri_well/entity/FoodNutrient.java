package com.example.nutri_well.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="foodNutrient")
public class FoodNutrient {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "nutrientId")
    private Nutrient nutrient;

    private double amount;

    public FoodNutrient(Food food, Nutrient nutrient, double amount) {
        this.food = food;
        this.nutrient = nutrient;
        this.amount = amount;
    }
}
