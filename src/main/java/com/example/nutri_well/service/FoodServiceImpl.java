package com.example.nutri_well.service;

import com.example.nutri_well.dao.FoodDAO;
import com.example.nutri_well.dto.CategoryResponseDTO;
import com.example.nutri_well.dto.FoodNutrientResponseDTO;
import com.example.nutri_well.dto.FoodResponseDTO;
import com.example.nutri_well.entity.Category;
import com.example.nutri_well.entity.Food;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
    private final FoodDAO dao;

    private int totalPage;

    @Override
    public List<FoodResponseDTO> searchByFoodName(String name, Pageable pageable) {
        Page<Food> foodpage = dao.searchByFoodName(name,pageable);
        Page<FoodResponseDTO> page = foodpage.map(FoodResponseDTO::of);
        this.totalPage = page.getTotalPages();
        //mainNutrients에 포함된 영양소만 dto에 추가
        for (FoodResponseDTO dto : page) {
            dto.setNutrientlist(findMainNutrients(dto));
        }
        System.out.println(page);

        return page.getContent();
    }


    @Override
    public List<FoodResponseDTO> searchByCategoryId(CategoryResponseDTO category, Pageable pageable) {
        ModelMapper mapper = new ModelMapper();
        Category entity = mapper.map(category, Category.class);
        Page<Food> foodpage = dao.searchByCategoryId(entity.getId(),pageable);
        Page<FoodResponseDTO> page = foodpage.map(FoodResponseDTO::of);
        this.totalPage = page.getTotalPages();
        for (FoodResponseDTO dto : page) {
            dto.setNutrientlist(findMainNutrients(dto));
        }
        return page.getContent();
    }

    @Override
    public int getTotalPages() {
        return totalPage;
    }


    public List<FoodNutrientResponseDTO> findMainNutrients(FoodResponseDTO dto){
        String[] mainNutrients = {"탄수화물","단백질","지방","당류"};
        List<FoodNutrientResponseDTO> filteredNutrients = new ArrayList<>();

        for (FoodNutrientResponseDTO nutrient : dto.getNutrientlist()) {
            if (Arrays.asList(mainNutrients).contains(nutrient.getName())) {
                filteredNutrients.add(nutrient);
            }
        }
        return filteredNutrients;
    }
}
