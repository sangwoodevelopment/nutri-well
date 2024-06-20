package com.example.nutri_well.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="food")
public class Food {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category categoryId;


    private String foodCode;
    private String product;

    private String manufacturer;
    private String servingSize;

    private Date creationDate;
    //하나의 식품은 여러개의 영양소를 가질수 있다.
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodNutrient> nutrientlist = new ArrayList<>();

    public Food(String name, Category categoryId, String foodCode, String product, String manufacturer, String servingSize, Date creationDate) {
        this.name = name;
        this.categoryId = categoryId;
        this.foodCode = foodCode;
        this.product = product;
        this.manufacturer = manufacturer;
        this.servingSize = servingSize;
        this.creationDate= creationDate;
    }
}
