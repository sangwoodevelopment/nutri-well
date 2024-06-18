package com.example.nutri_well.controller;

import com.example.nutri_well.dto.CategoryResponseDTO;
import com.example.nutri_well.dto.FoodResponseDTO;
import com.example.nutri_well.repository.CategoryRepository;
import com.example.nutri_well.service.CategoryService;
import com.example.nutri_well.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public final class SearchFoodController {
    private final FoodService foodService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @GetMapping("/search")
    public ModelAndView searchPage(@RequestParam String query, @RequestParam int page,
                                   @RequestParam int size, @RequestParam(required = false) Long category,
                                    @RequestParam(required = false) String nutrients ){
        if (nutrients != null){
            List<String> nutrientQuery = new ArrayList<>();
            if(nutrients.contains("\\|")){
                nutrientQuery = Arrays.stream(nutrients.split("\\|")).toList();
            }else{
                nutrientQuery.add(nutrients);
            }
        }

        ModelAndView mav = new ModelAndView("/search/shop");
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
        System.out.println(totalpage);
        mav.addObject("query",query);
        mav.addObject("currentPage",page);
        mav.addObject("totalPage",totalpage);
        mav.addObject("foodlist",foodlist);
        mav.addObject("categories",categories);
        return mav;
    }
    @GetMapping("/show")
    public ModelAndView searchPage2( ){
        ModelAndView mav = new ModelAndView("search/shop");
        return mav;
    }
}
