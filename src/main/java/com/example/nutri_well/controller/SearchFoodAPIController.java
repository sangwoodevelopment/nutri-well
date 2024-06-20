package com.example.nutri_well.controller;

import com.example.nutri_well.dto.CategoryResponseDTO;
import com.example.nutri_well.dto.FoodResponseDTO;
import com.example.nutri_well.dto.SearchPageWrapperDTO;
import com.example.nutri_well.service.CategoryService;
import com.example.nutri_well.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public final class SearchFoodAPIController {
    private final FoodService foodService;
    private final CategoryService categoryService;
//    @GetMapping("/search")
//    public List<FoodResponseDTO> searchPage2(@RequestParam("query") String query, @RequestParam("page") int page,
//                                   @RequestParam("size") int size){
//        return foodService.searchByFoodName(query,
//                PageRequest.of(page,size, Sort.unsorted()));
//    }

    @GetMapping("/search")
    public SearchPageWrapperDTO searchPage(@RequestParam("query") String query, @RequestParam("page") int page,
                                           @RequestParam("size") int size){
        List<FoodResponseDTO> fooddto= foodService.searchByFoodName(query,
                PageRequest.of(page,size, Sort.unsorted()));
        List<CategoryResponseDTO> categorydto = categoryService.findByParentCategoryIsNull();

        return new SearchPageWrapperDTO(categorydto,fooddto);
    }
}
