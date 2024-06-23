package com.example.nutri_well.controller;

import com.example.nutri_well.dto.CategoryResponseDTO;
import com.example.nutri_well.dto.FoodResponseDTO;
import com.example.nutri_well.dto.SearchPageWrapperDTO;
import com.example.nutri_well.service.CategoryService;
import com.example.nutri_well.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public final class SearchFoodAPIController {
    private final FoodService foodService;
    private final CategoryService categoryService;

    @GetMapping("/food/detail")
    public FoodResponseDTO showPage(@RequestParam("foodname") String foodname){
        return foodService.findByName(foodname);
    }
    @GetMapping("/search")
    public SearchPageWrapperDTO searchPage(@RequestParam(name = "query",required = false) String query, @RequestParam("page") int page,
                                           @RequestParam("size") int size, @RequestParam(name="category",required = false) Long category,
                                           @RequestParam(name="nutrient",required = false) String nutrients ){
        if (nutrients != null){
            List<String> nutrientQuery = new ArrayList<>();
            if(nutrients.contains("\\|")){
                nutrientQuery = Arrays.stream(nutrients.split("\\|")).toList();
            }else{
                nutrientQuery.add(nutrients);
            }
        }

        List<FoodResponseDTO> foodlist = null;
        List<CategoryResponseDTO> categories = null;
        int totalpage = 0;

        if(category == null || category == 0){
            foodlist = foodService.searchByFoodName(query,
                    PageRequest.of(page,size, Sort.unsorted()));
            totalpage = foodService.getTotalPages();
            categories = categoryService.findByParentCategoryIsNull();

        } else {
            CategoryResponseDTO categoryDTO = categoryService.findbyId(category);
            foodlist = foodService.searchByCategoryId(categoryDTO,
                    PageRequest.of(page,size));
            totalpage = foodService.getTotalPages();
            categories = categoryService.findByParentCategoryIsNull();
        }

        return new SearchPageWrapperDTO(categories,foodlist);
    }



}
