package com.example.nutri_well.repository;

import com.example.nutri_well.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FoodRepository extends JpaRepository<Food,Long> {
    Food findByName(String name);
    //@Query(value = "SELECT f FROM Food as f WHERE f.name LIKE %:name%")
    Page<Food> findByNameContaining(@Param("name") String name, Pageable pageable);
    //Slice
    //Page<Food> findByCategoryIdContaining(@Param("category") Category category, Pageable pageable);
    @Query("SELECT f FROM Food f WHERE f.categoryId.id = :categoryId")
    Page<Food> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
}
