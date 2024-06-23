package com.example.nutri_well.controller;

import com.example.nutri_well.dto.CategoryResponseDTO;
import com.example.nutri_well.dto.FoodResponseDTO;
import com.example.nutri_well.repository.CategoryRepository;
import com.example.nutri_well.repository.FoodRepository;
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

    @GetMapping("/search")
    public ModelAndView searchPage(@RequestParam("query") String query, @RequestParam("page") int page,
                                   @RequestParam("size") int size, @RequestParam(name="category",required = false) Long category,
                                    @RequestParam(name="nutrients",required = false) List<String> nutrients ){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.unsorted());
        List<FoodResponseDTO> foodlist = null;

        if(category == null || category == 0){
            if(nutrients != null){
                System.out.println(nutrients.size());
                foodlist = foodService.findAllByNutrientsNotIn(query,nutrients,pageRequest);
            }else{
                foodlist = foodService.searchByFoodName(query, pageRequest);
            }
        } else {
            CategoryResponseDTO categoryDTO = categoryService.findbyId(category);
            foodlist = foodService.searchByCategoryId(categoryDTO,pageRequest);
        }

        int totalpage = foodService.getTotalPages();
        List<CategoryResponseDTO> categories = categoryService.findByParentCategoryIsNull();

        ModelAndView mav = new ModelAndView("/search/shop");
        mav.addObject("query",query);
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
