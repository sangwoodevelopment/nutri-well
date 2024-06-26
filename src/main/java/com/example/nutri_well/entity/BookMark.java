package com.example.nutri_well.entity;

import com.example.nutri_well.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookMark", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"foodId", "userId"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookMark {
    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private boolean preferredState = false;

    @Column(nullable = false)
    private boolean excludedState = false;

    public BookMark(Food food, User user, boolean preferredState, boolean excludedState) {
        this.food = food;
        this.user = user;
        this.preferredState = preferredState;
        this.excludedState = excludedState;
    }
}
