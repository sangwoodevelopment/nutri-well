package com.example.nutri_well.dao;

import com.example.nutri_well.entity.Food;
import com.example.nutri_well.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoodDAOImpl implements FoodDAO{
    private final FoodRepository foodRepository;

    @Override
    public Page<Food> searchByFoodName(String name , Pageable pageable) {
        System.out.println(foodRepository.findByNameContaining(name, pageable).getTotalElements());
        return foodRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<Food> searchByCategoryId(Long categoryid, Pageable pageable) {
        return  foodRepository.findByCategoryId(categoryid,pageable);
    }

    @Override
    public int getTotalPages(Page<Food> page) {
        return page.getTotalPages();
    }

    @Override
    public Food findByName(String name) {
        return foodRepository.findByName(name);
    }
}
