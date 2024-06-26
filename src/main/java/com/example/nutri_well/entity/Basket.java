package com.example.nutri_well.entity;

import com.example.nutri_well.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue
    private Long id;

    private float energy;
    private float sodium;
    private float carbo;
    private float sugar;
    private float fat;
    private float trans_fat;
    private float saturated_fat;
    private float Cholesterol;
    private float protein;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userid;
    @CreationTimestamp
    private Date startDate;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food foodid;

    @Transient // 기초대사량 계산식 적용해야함
    private String basel_metabolism;

    public Basket(User userid, String type, Food foodid) {
        this.userid = userid;
        this.type = type;
        this.foodid = foodid;
    }

    public Basket(Long id, User userid, String type) {
        this.id = id;
        this.userid = userid;
        this.type = type;
    }
}
