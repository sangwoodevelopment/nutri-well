package com.example.nutri_well.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email; //=> 로그인할때는 email + password . UNIQUE

    @Column(nullable = false)
    private String gender; //수정가능

    @Column
    private float weight; //수정가능

    @Column
    private String birth; //수정가능

    @Column
    private String tel; //수정가능

    @Column(nullable = false)
    private boolean state; //가입, 탈퇴여부

    @Column
    private float height; //수정가능

    @Column
    private int basel_metabolism; //수정가능

    @Column(nullable = false)
    private String password;

    @Column
    private String picture; //수정가능

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String password, String picture, Role role, String gender, boolean state, String birth, String tel) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.role = role;
        this.gender = gender;
        this.state = state;
        this.birth = birth;
        this.tel = tel;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<myCalendar> calendars;

    public User update(String name, String picture) {
        this.username = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }



}
