package com.example.nutri_well.dao;

import com.example.nutri_well.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    List<Category> findByparentCategoryIsNull();
    Category findbyId(Long id);
}
