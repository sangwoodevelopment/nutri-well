package com.example.nutri_well.service;

import com.example.nutri_well.model.User;
import com.example.nutri_well.model.myCalendar;
import com.example.nutri_well.repository.CalendarRepository;
import com.example.nutri_well.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    public myCalendar addCalendarEntry(Long userId, Date date, int percentage) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        myCalendar calendar = myCalendar.builder()
                .user(user)
                .calDate(date)
                .percentage(percentage)
                .build();

        return calendarRepository.save(calendar);
    }

    public List<myCalendar> getCalendarEntries(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        return calendarRepository.findByUser(user);
    }

    public List<myCalendar> getCalendarEntriesByDate(Long userId, Date date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        return calendarRepository.findByUserAndCalDate(user, date);
    }
}
