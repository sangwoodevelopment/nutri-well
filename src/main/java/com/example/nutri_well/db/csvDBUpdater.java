package com.example.nutri_well.db;

import com.example.nutri_well.entity.Category;
import com.example.nutri_well.entity.Food;
import com.example.nutri_well.entity.FoodNutrient;
import com.example.nutri_well.entity.Nutrient;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class csvDBUpdater implements DBUpdater{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateDatabase(String filePath) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            List<Food> foodlist = new ArrayList<>();
            List<Nutrient> nutrientlist = new ArrayList<>();
            List<FoodNutrient> foodNutrientlist = new ArrayList<>();

            String[] header = records.get(0); // 17~99가 영양소
            String name = "";
            String unit = "";
            for (int i = 17; i < 100; i++) {
                name = header[i].replaceAll("[()]", "")
                        .replaceAll("[^가-힣]", ""); // 한글 추출
                unit = header[i].replaceAll("[()]", "")
                        .replaceAll("[^a-zA-Z]", ""); // 단위 추출
                nutrientlist.add(new Nutrient(name, unit));
            }
            entityManager.persist(nutrientlist);
            records.remove(0); // 헤더 삭제

            for (String[] record : records) {
                Category category;
                Food food;

                if (record[7].equals("해당없음")) {
                    category = new Category(record[7], null); // 7 대분류
                } else {
                    category = new Category(record[9], new Category(record[7], null)); // 9 소분류
                }

                food = new Food("빵", category, "00001", "상용제품", "롯데제과", "100",null);
                foodlist.add(food);

                String[] nutrients = Arrays.copyOfRange(record, 17, 100);
                for (int i = 0; i < nutrients.length; i++) {
                    String nutrientValueStr = nutrients[i].trim();
                    double nutrientValue = nutrientValueStr.isEmpty() ? 0 : Double.parseDouble(nutrientValueStr);
                    FoodNutrient foodNutrient = new FoodNutrient(food, nutrientlist.get(i), nutrientValue);
                    foodNutrientlist.add(foodNutrient);
                }
            }
            entityManager.persist(foodlist);
            entityManager.persist(foodNutrientlist);

        } catch (CsvException e) {
            throw new IOException("CSV 파일을 읽는 중 오류 발생", e);
        }
    }
}
