package com.example.nutri_well.dao;

import com.example.nutri_well.entity.Basket;
import com.example.nutri_well.entity.Food;

public interface BasketDAO {
	Basket insert(Basket dto) ; // 캘린더 등록
	void delete(Food foodid); // 담은 식품 삭제
}