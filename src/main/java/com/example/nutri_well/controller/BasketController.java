package com.example.nutri_well.controller;

import com.example.nutri_well.entity.Basket;
import com.example.nutri_well.model.User;
import com.example.nutri_well.service.BasketService;
import com.example.nutri_well.entity.Food;
import com.example.nutri_well.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/basket")
@RequiredArgsConstructor
@SessionAttributes("basket")
public class BasketController {
    private final BasketService service;
    private final UserService userService;

    @GetMapping("/read")
    public String basketView(Integer baselMetabolism, Model model) {
        Optional<User> user = userService.findByBaselMetabolism(baselMetabolism);
        System.out.println(user);
        model.addAttribute("user", user);
        return "basket/basket";
//        return "test1/test";
    }
//    public String basketView() {
//    }

    // 담기버튼을 누른 식품 보여주기 (코드 틀만 작성함)
//    @GetMapping("/food")
//    public String basketFoodView(Model model, HttpSession session) {
//        Basket basket = (Basket) session.getAttribute("nutri"); // Object 수정해야함
//        model.addAttribute("basket", basket);
//        return "basket/basket";
//    }

    // 담은 식품 삭제
    @GetMapping("/delete")
    public String delete(Food foodid) {
        service.delete(foodid);
        return "redirect:/basket/basket";
    }

//    // 담은 식품 캘린더에 등록 (미완성)
//    @PostMapping("/insert")
//    public String insert() {
//        service.insert();
//        return "mypage/calendar";
//    }
}
