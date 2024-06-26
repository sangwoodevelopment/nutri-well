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

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;
    @CreationTimestamp
    private Date startDate;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food foodId;

    @Transient // 기초대사량 계산식 적용해야함
    private String basel_metabolism;

    public Basket(User userId, String type, Food foodId) {
        this.userId = userId;
        this.type = type;
        this.foodId = foodId;
    }

    public Basket(Long id, User userId, String type) {
        this.id = id;
        this.userId = userId;
        this.type = type;
    }
}
