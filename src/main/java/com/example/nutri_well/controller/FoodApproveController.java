package com.example.nutri_well.controller;

import com.example.nutri_well.dto.CategoryResponseDTO;
import com.example.nutri_well.dto.FoodApproveRequestDTO;
import com.example.nutri_well.dto.FoodApproveResponseDTO;
import com.example.nutri_well.dto.NutrientResponseDTO;
import com.example.nutri_well.service.CategoryService;
import com.example.nutri_well.service.FoodApproveServie;
import com.example.nutri_well.service.NutrientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food-approve")
@RequiredArgsConstructor
public class FoodApproveController {

    private final FoodApproveServie foodApproveService;
    private final CategoryService categoryService;
    private final NutrientService nutrientService;

    @GetMapping("/request")
    public String requestFoodApprovalForm(Model model) {
        List<CategoryResponseDTO> categories = categoryService.findByParentCategoryIsNull(); //부모가 없는 카테고리만 가져오기
        List<NutrientResponseDTO> nutrients = nutrientService.getAllNutrients(); //모든 영양소 가져오기
        model.addAttribute("categories", categories);
        model.addAttribute("nutrients", nutrients);
        model.addAttribute("foodApproveRequest", new FoodApproveRequestDTO());
        return "food-approve/request";
    }

    @PostMapping("/request")
    public String requestFoodApproval(@ModelAttribute FoodApproveRequestDTO foodApproveRequestDTO, Model model) {
        FoodApproveResponseDTO response = foodApproveService.requestFoodApproval(foodApproveRequestDTO);
        model.addAttribute("response", response);
        return "food-approve/request-success";
    }

    @GetMapping("/approve/{id}")
    public String approveFood(@PathVariable Long id, Model model) {
        FoodApproveResponseDTO response = foodApproveService.approveFood(id);
        model.addAttribute("response", response);
        return "food-approve/approve-success";
    }

    @GetMapping("/list")
    public String listFoodApprovals(Model model) {
        List<FoodApproveResponseDTO> approvals = foodApproveService.getAllFoodApprovals();
        model.addAttribute("approvals", approvals);
        return "food-approve/list";
    }
}
