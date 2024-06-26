package com.example.nutri_well.repository;

import com.example.nutri_well.entity.Basket;
import com.example.nutri_well.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Long> {
}
