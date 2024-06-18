package com.example.nutri_well.controller;

import com.example.nutri_well.model.User;
import com.example.nutri_well.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userService.findByUsername(username);
        Map<String, String> response = new HashMap<>();
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            response.put("username", user.getUsername());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/checkSession")
    public ResponseEntity<Map<String, Object>> checkSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("loggedIn", true);
            response.put("username", user.getUsername());
        } else {
            response.put("loggedIn", false);
        }
        return ResponseEntity.ok(response);
    }
}
