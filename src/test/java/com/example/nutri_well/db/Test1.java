package com.example.nutri_well.db;

import com.example.nutri_well.dto.FoodResponseDTO;
import com.example.nutri_well.entity.Food;
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
    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("탄수화물");
        list.add("단백질");

        Page<Food> list2 = foodRepository.findAllByNutrientsNotIn("%마카롱%", list, Pageable.unpaged());
        List<FoodResponseDTO> list3 =  list2.stream().map(FoodResponseDTO::of).toList();

        Page<Food> DFDF = foodRepository.findByNameContaining("마카롱", Pageable.unpaged());
        List<FoodResponseDTO> list4 =DFDF.stream().map(FoodResponseDTO::of).toList();
        System.out.println(list3.size()+","+list3.size()/10);
        System.out.println(list4.size()+","+list4.size()/10);

    }
}
