package com.example.nutri_well.model;

import com.example.nutri_well.entity.BookMark;
import com.example.nutri_well.entity.Food;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",sequenceName = "user_sequence", allocationSize = 1)
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
    private Date birth; //수정가능

    @Column
    private String tel; //수정가능

    @Column(nullable = false)
    private boolean state = true; //가입, 탈퇴여부

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
    public User(String name, String email, String password, String picture, Role role, String gender, boolean state, Date birth, String tel) {
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
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<myCalendar> calendars;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookMark> bookmark;

    public User update(String name, String picture) {
        this.username = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }



}
