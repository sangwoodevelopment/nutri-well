package com.example.nutri_well.service;

import com.example.nutri_well.dto.SignUpDTO;
import com.example.nutri_well.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SignUpService {
    //회원가입, 정보 조회, 수정
    //addmember시 boolean도 추가해줘야함
    User registerUser(SignUpDTO MemberProfile);
    Optional<User> findByemail(String email);
    Optional<User> findBypassword(String password);
    Optional<User> findBygender(String gender);
    Optional<User> findByweight(float weight);
    Optional<User> findByheight(float height);
    Optional<User> findBytel(String tel);
    Optional<User> findBypicture(String picture);
//    User updateUser(Long id, User updateUserProfile);
//
//    @Transactional
//    //업데이트
//    User updateUser(Long id, User updateUserProfile);
//
//    void deleteUser(Long id);
}
