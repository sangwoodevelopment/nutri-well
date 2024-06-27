package com.example.nutri_well.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "CALENDAR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class myCalendar {
    @Id
    @GeneratedValue
    private Long calendarId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate calDate;

    @Column(nullable = false)
    private int percentage;
}
