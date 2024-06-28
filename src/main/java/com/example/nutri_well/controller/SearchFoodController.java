package com.example.nutri_well.controller;

import com.example.nutri_well.dto.CategoryResponseDTO;
import com.example.nutri_well.dto.FoodResponseDTO;
import com.example.nutri_well.repository.CategoryRepository;
import com.example.nutri_well.repository.FoodRepository;
import com.example.nutri_well.service.CategoryService;
import com.example.nutri_well.service.FoodService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ModelAndView searchPage(@RequestParam("query") String query, @RequestParam("page") int page, @RequestParam("size") int size,
                                   @RequestParam(name="nutrients",required = false) List<String> nutrients,
                                   @RequestParam(name="min",required = false) Integer min,
                                   @RequestParam(name="max",required = false) Integer max){

        PageRequest pageRequest = PageRequest.of(page, size, Sort.unsorted());
        List<FoodResponseDTO> foodlist = null;

        if(nutrients != null || min != null || max != null){
            //foodlist = foodService.findAllByNutrientsNotIn(query,nutrients,pageRequest);
            foodlist = foodService.findAllByNutrientsInRange(query,nutrients,min,max,pageRequest);
        }else{
            foodlist = foodService.searchByFoodName(query, pageRequest);
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

    @GetMapping("/searchCategory")
    public ModelAndView searchCategoryPage(@RequestParam(name="category",required = false) Long category,
                                           @RequestParam("page") int page, @RequestParam("size") int size,
                                           @RequestParam(name="nutrients",required = false) List<String> nutrients,
                                           @RequestParam(name="min",required = false) Integer min,
                                           @RequestParam(name="max",required = false) Integer max){

        PageRequest pageRequest = PageRequest.of(page, size, Sort.unsorted());
        List<FoodResponseDTO> foodlist = null;

        CategoryResponseDTO categoryDTO = categoryService.findbyId(category);
        if(nutrients != null || min != null || max != null){
            //foodlist = foodService.findAllByNutrientsNotIn(categoryDTO,nutrients,pageRequest);
            foodlist = foodService.findAllByNutrientsInRange(category,nutrients,min,max,pageRequest);
        }else {
            foodlist = foodService.searchByCategoryId(categoryDTO, pageRequest);
        }
        int totalpage = foodService.getTotalPages();

        List<CategoryResponseDTO> categories = categoryService.findByParentCategoryIsNull();
        ModelAndView mav = new ModelAndView("/search/shop");
        mav.addObject("totalPage",totalpage);
        mav.addObject("category",category);
        mav.addObject("foodlist",foodlist);
        mav.addObject("categories",categories);
        return mav;
    }

    @GetMapping("/show")
    public ModelAndView searchPage2( ){
        ModelAndView mav = new ModelAndView("search/shop");
        return mav;
    }

    @PostMapping("/insert")
    public String nutriInsert(@RequestParam("foodCode") String foodCode, HttpSession session) {
        //add to cart버튼 누르 foodcode 파라미터 넘기
        //foodcode food객체를 조회해
        //세션 foodcode 세션 attributename으로 food 객체를 value로
        FoodResponseDTO food = foodService.findByFoodCode(foodCode);
        System.out.println(food);
        session.setAttribute("food" ,food);
//        model.addAttribute("code",foodCode);
//        System.out.println(foodCode);
//        Food food = (Food) session.getAttribute("basket"); //FOod로 바꾸기
//        if (food == null) {
//            food = new Food(); // 적절한 Basket 클래스 생성 로직이 필요합니다.
//        }
//        System.out.println(food);
//        session.setAttribute("basket", food.getFoodCode()); // Object 수정해야함
//        System.out.println(food);
//        model.addAttribute("basket", food);
        return "redirect:/basket/read";
    }
}
