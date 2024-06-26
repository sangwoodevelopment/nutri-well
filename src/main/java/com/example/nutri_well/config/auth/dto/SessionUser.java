package com.example.nutri_well.config.auth.dto;

import com.example.nutri_well.model.Role;
import com.example.nutri_well.model.User;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class SessionUser implements Serializable {
    //User클래스를 그대로 사용하면 직렬화를 구현하지 않았기 때문에 에러가 발생한다. 그래서 직렬화된 SessionUser클래스를 만들어준다.
    //객체를 바이트스트림으로 변환하여 세션에 저장하기 위함. 이렇게 직렬화 하지 않으면 세션에 저장할 수 없음.
    //User 클래스는 엔티티 클래스이며 직렬화가 되지 않기때문에 별도의 직렬화 클래스를 만든것.
    private Long userId;
    private String name;
    private String email;
    private String gender;
    private Float weight;
    private Date birth;
    private String tel;
    private boolean state;
    private Float height;
    private int basel_metabolism;
    private String password;
    private String picture;
    private Role role;

//    public SessionUser(User user){
//        this.name = user.getUsername();
//        this.email = user.getEmail();
//        this.picture = user.getPicture();
//    }

    public SessionUser(User user) {
        this.userId = user.getUserId();
        this.name = user.getUsername();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.weight = user.getWeight();
        this.birth = user.getBirth();
        this.tel = user.getTel();
        this.state = user.isState();
        this.height = user.getHeight();
        this.basel_metabolism = user.getBasel_metabolism();
        this.password = user.getPassword();
        this.picture = user.getPicture();
        this.role = user.getRole();
    }
}
