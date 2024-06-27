package com.example.nutri_well.dao;

import com.example.nutri_well.entity.Basket;
import com.example.nutri_well.entity.Food;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BasketDAOImpl implements BasketDAO {
    private final EntityManager entityManager;

    @Override
    public void insert(Basket dto) {
        // (코드 작성중)
    }

    @Override
    public void delete(Food foodid) {
        Basket basket = entityManager.find(Basket.class, foodid);
        entityManager.remove(basket);
    }
}