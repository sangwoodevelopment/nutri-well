package com.example.nutri_well.service;

import com.example.nutri_well.model.User;
import com.example.nutri_well.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        // 사용자 정보를 업데이트합니다.
        userRepository.save(user);
    }

    public User getCurrentUser(String email) {
        // 현재 로그인한 사용자 정보를 이메일로 가져옵니다.
        return userRepository.findByEmail(email).orElse(null);
    }
}
