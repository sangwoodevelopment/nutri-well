package com.example.nutri_well.service;

import com.example.nutri_well.dto.BasketResponseDTO;
import com.example.nutri_well.entity.Basket;
import com.example.nutri_well.entity.Food;

public interface BasketService {
	BasketResponseDTO insert(Basket dto) ;
	void delete(Food foodid);
}
