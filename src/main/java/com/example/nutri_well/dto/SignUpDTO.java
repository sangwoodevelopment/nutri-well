package com.example.nutri_well.dto;

import com.example.nutri_well.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Data
public class SignUpDTO {
    private String username;
    private String email;
    private String password;
    private String birth;
    private String gender;
    private String weight;
    private String height;
    private String tel;
    private int basel_metabolism;
    private String picture;
    private Role role = Role.USER;
    private boolean state = true;

}
