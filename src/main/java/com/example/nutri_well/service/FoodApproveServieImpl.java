package com.example.nutri_well.service;

import com.example.nutri_well.dao.FoodDAO;
import com.example.nutri_well.dto.FoodApproveRequestDTO;
import com.example.nutri_well.dto.FoodApproveResponseDTO;
import com.example.nutri_well.entity.*;
import com.example.nutri_well.model.User;
import com.example.nutri_well.repository.FoodApproveRepository;
import com.example.nutri_well.repository.NutrientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodApproveServieImpl implements FoodApproveServie{
    private final FoodApproveRepository foodApproveRepository;
    private final FoodDAO foodDAO;
    private final UserService userService;
    private final NutrientRepository nutrientRepository;

    @Override
    public FoodApproveResponseDTO requestFoodApproval(FoodApproveRequestDTO requestDTO) {
        Optional<User> user = userService.findByUserEmail(requestDTO.getUserEmail());

        if (user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user email");
        }

        FoodApprove foodApprove = new FoodApprove();
        foodApprove.setName(requestDTO.getName());
        foodApprove.setCategoryId(requestDTO.getCategoryId());
        foodApprove.setProduct(requestDTO.getProduct());
        foodApprove.setManufacturer(requestDTO.getManufacturer());
        foodApprove.setServingSize(requestDTO.getServingSize());
        foodApprove.setRequestDate(new Date());
        foodApprove.setUser(user.get());

        List<FoodNutrientApprove> nutrients = requestDTO.getNutrients().stream()
                .map(n -> {
                    Nutrient nutrient = nutrientRepository.findById(n.getNutrientId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid nutrient ID"));
                    return new FoodNutrientApprove(foodApprove, nutrient, n.getAmount());
                })
                .collect(Collectors.toList());

        foodApprove.setNutrientlist(nutrients);
        foodApproveRepository.save(foodApprove);

        return FoodApproveResponseDTO.of(foodApprove);
    }

    @Override
    public FoodApproveResponseDTO approveFood(Long requestId) {
        FoodApprove foodApprove = foodApproveRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));

        foodApprove.setApproved(true);
        foodApprove.setApprovalDate(new Date());
        foodApproveRepository.save(foodApprove);

        Food food = new Food(
                foodApprove.getName(),
                foodApprove.getCategoryId(),
                null, // 식품코드는 null
                foodApprove.getProduct(),
                foodApprove.getManufacturer(),
                foodApprove.getServingSize(),
                foodApprove.getApprovalDate()
        );

        for (FoodNutrientApprove nutrientApprove : foodApprove.getNutrientlist()) {
            food.getNutrientlist().add(new FoodNutrient(food, nutrientApprove.getNutrient(), nutrientApprove.getAmount()));
        }

        food = foodDAO.save(food);

        return FoodApproveResponseDTO.of(foodApprove);
    }

    @Override
    public List<FoodApproveResponseDTO> getAllFoodApprovals() {
        List<FoodApprove> foodApprovals = foodApproveRepository.findAll();
        return foodApprovals.stream()
                .map(FoodApproveResponseDTO::of)
                .collect(Collectors.toList());
    }
}
