package com.example.nutri_well.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date calDate;

    @Column(nullable = false)
    private int percentage;
}
