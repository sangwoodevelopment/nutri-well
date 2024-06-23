package com.example.nutri_well.service;

import com.example.nutri_well.dao.BasketDAO;
import com.example.nutri_well.entity.Basket;
import com.example.nutri_well.entity.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {
    private  final BasketDAO dao;

    @Override
    public void insert(Basket dto) {
        dao.insert(dto);
    }

    @Override
    public void delete(Food foodid) {
        dao.delete(foodid);
    }
}
