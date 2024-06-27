package com.example.nutri_well.controller;

import com.example.nutri_well.dto.SignUpDTO;
import com.example.nutri_well.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//뷰에서 요청 -> 요청을 받아주는 역활
//서비스에 있는 메소드를 부르면서 연결
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class SignUpController {

    private final SignUpService memberService;

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("memberSignUpDTO",new SignUpDTO());
        return "signup/signup";
    }
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("memberSignUpDTO") SignUpDTO memberSignUpDTO, Model model) {
        try {
            memberService.registerUser(memberSignUpDTO);
            return "redirect:/member/signUpsuccess";
        } catch (IllegalArgumentException e) {
            model.addAttribute("에러메세지","회원가입실패"+e.getMessage());
            return "signup/signup";
        }
    }

    @GetMapping("/signUpsuccess")
    public String signupSuccess(){
        //회원가입성공시 success페이지로 이동
        return "signUpsuccess";
    }
    @GetMapping("/error")
    public String signupError(@RequestParam("signUp_error")String loginError, Model model){
        //회원가입실패시 signup페이지에 머무르기
        model.addAttribute("errorMsg","회원가입실패");
        return "signup/signup";
    }
    @GetMapping("/main")
    public String main(){

        return "index";
    }
}
