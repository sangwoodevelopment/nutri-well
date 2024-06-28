package com.example.nutri_well.db;

import com.example.nutri_well.dto.FoodResponseDTO;
import com.example.nutri_well.entity.Food;
import com.example.nutri_well.repository.BookMarkRepository;
import com.example.nutri_well.repository.FoodRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class Test1 {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private BookMarkRepository bookMarkRepository;
    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("단백질");
        list.add("에너지");
        Page<Food> food = foodRepository.findAllByNutrientsInRange("%그린티%", list, 1, 500, PageRequest.of(0, 10));
        List<FoodResponseDTO> content = food.map(FoodResponseDTO::of).getContent();
        System.out.println(content);
    }
}
