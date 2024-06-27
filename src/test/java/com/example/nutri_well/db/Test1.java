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
import org.springframework.data.domain.Pageable;
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
        List<Food> top5Foods = bookMarkRepository.findTop5Foods();
        for (Food food : top5Foods) {
            System.out.println(food);
        }
    }
}
