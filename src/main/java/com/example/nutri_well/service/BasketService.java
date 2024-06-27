package com.example.nutri_well.service;

import com.example.nutri_well.entity.Basket;
import com.example.nutri_well.entity.Food;

public interface BasketService {
	void insert(Basket dto) ;
	void delete(Food foodid);
}
