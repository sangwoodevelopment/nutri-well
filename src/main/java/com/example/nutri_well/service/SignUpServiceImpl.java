package com.example.nutri_well.service;

import com.example.nutri_well.dto.SignUpDTO;
import com.example.nutri_well.model.User;
import com.example.nutri_well.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
@Slf4j
@Service
public class SignUpServiceImpl implements SignUpService {
    //erp jpa 참고
    //중복체크, 널체크, 정보저장

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    //이메일 중복 체크
    public User registerUser(SignUpDTO memberSignUpDTO){
        log.debug("memberSignUpDTO={}",memberSignUpDTO);
        System.out.println("memberSignUpDTO={}"+memberSignUpDTO);
        if (userRepository.findByEmail(memberSignUpDTO.getEmail()).isPresent()) {

            throw new IllegalArgumentException("이미 사용 중인 이메일입니다");
        }
        //널체크
        if (memberSignUpDTO.getUsername() == null || memberSignUpDTO.getEmail() == null || memberSignUpDTO.getGender() == null || memberSignUpDTO.getPassword() == null) {

            throw new IllegalArgumentException("필수 입력 필드가 누락되었습니다");
        }
            SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
            Date birthDate;
        try{
            birthDate = sf.parse(memberSignUpDTO.getBirth());
        }catch (ParseException e){
            throw new IllegalArgumentException("유효하지 않은 생년월일 형식입니다 yyyy/MM/dd");
        }

                 User user = User.builder()
                         .username(memberSignUpDTO.getUsername())
                        .email(memberSignUpDTO.getEmail())
                        .password(memberSignUpDTO.getPassword())
                        .birth(birthDate)
                        .gender(memberSignUpDTO.getGender())
                        .weight(Float.parseFloat(memberSignUpDTO.getWeight()))
                        .height(Float.parseFloat(memberSignUpDTO.getHeight()))
                        .tel(memberSignUpDTO.getTel())
                        .picture(memberSignUpDTO.getPicture())
                         .basel_metabolism(memberSignUpDTO.getBasel_metabolism())
                         .role(memberSignUpDTO.getRole())
                         .state(true)
                        .build();
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByemail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findBypassword(String password) {
        return userRepository.findByPassword(password);
    }

    @Override
    public Optional<User> findBygender(String gender) {
        return userRepository.findByGender(gender);
    }

    @Override
    public Optional<User> findByweight(float weight) {
        return userRepository.findByWeight(weight);
    }

    @Override
    public Optional<User> findByheight(float height) {
        return userRepository.findByHeight(height);
    }

    @Override
    public Optional<User> findBytel(String tel) {
        return userRepository.findByTel(tel);
    }

    @Override
    public Optional<User> findBypicture(String picture) {
        return userRepository.findByPicture(picture);
    }

//    @Override
//    public User updateUser(Long id, Member updateUserProfile) {
//        return null;
//    }
//
//
//    @Override
//    @Transactional
//    //업데이트
//    public User updateUser(Long id, User updateUserProfile) {
//        User oldMemberProfile = userRepository.findById(id).orElseThrow(()
//                -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));
//        //이메일 중복 체크
//        if (!oldMemberProfile.getEmail().equals(updateUserProfile.getEmail()) &&
//                userRepository.findByEmail(updateUserProfile.getEmail()).isPresent()) {
//            throw new IllegalArgumentException("이미 사용 중인 이메일입니다");
//        }
//            oldMemberProfile.setEmail(updateUserProfile.getEmail());
//            oldMemberProfile.setPassword(updateUserProfile.getPassword());
//            oldMemberProfile.setGender(updateUserProfile.getGender());
//            oldMemberProfile.setWeight(updateUserProfile.getWeight());
//            oldMemberProfile.setHeight(updateUserProfile.getHeight());
//            oldMemberProfile.setTel(updateUserProfile.getTel());
//            oldMemberProfile.setPicture(updateUserProfile.getPicture());
//
//        return userRepository.save(oldMemberProfile);
//    }
//        @Override
//        @Transactional
//        //삭제
//        public void deleteUser (Long id){
//            if(!userRepository.existsById(id)){
//                throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
//            }
//            userRepository.deleteById(id);
//        }
    }
