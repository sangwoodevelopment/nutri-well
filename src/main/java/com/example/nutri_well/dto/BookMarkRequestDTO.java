package com.example.nutri_well.dto;

import com.example.nutri_well.entity.Food;
import com.example.nutri_well.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkRequestDTO {
    private Long foodId;
    private Long userId;
    private boolean preferredState;
    private boolean excludedState;
}
