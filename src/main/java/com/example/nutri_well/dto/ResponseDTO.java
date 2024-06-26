package com.example.nutri_well.dto;

import com.example.nutri_well.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDTO {
    private Long UserId;
    private String username;
    private String email;

    public ResponseDTO(User user){
        this.UserId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

}
