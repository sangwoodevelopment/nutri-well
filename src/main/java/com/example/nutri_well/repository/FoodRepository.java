package com.example.nutri_well.repository;

import com.example.nutri_well.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FoodRepository extends JpaRepository<Food,Long> {
    Food findByName(String name);
    Page<Food> findByNameContaining(@Param("name") String name, Pageable pageable);

    @Query("SELECT f FROM Food f WHERE f.categoryId.id = :categoryId")
    Page<Food> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query("SELECT f FROM Food f " +
            "WHERE f.name LIKE :name AND " +
            "NOT EXISTS (SELECT 1 FROM f.nutrientlist fn WHERE fn.nutrient.name IN :names)")
    Page<Food> findAllByNutrientsNotIn(@Param("name")String foodname, @Param("names") List<String> names, Pageable pageable);

    @Query("SELECT f FROM Food f " +
            "WHERE f.categoryId.id = :categoryId AND " +
            "NOT EXISTS (SELECT 1 FROM f.nutrientlist fn WHERE fn.nutrient.name IN :names)")
    Page<Food> findAllByNutrientsNotIn(@Param("categoryId")Long category, @Param("names") List<String> names, Pageable pageable);

    Food findByFoodCode(String foodcode);
}
