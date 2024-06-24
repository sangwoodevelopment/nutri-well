package com.example.nutri_well.controller;

import com.example.nutri_well.model.myCalendar;
import com.example.nutri_well.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/add")
    public myCalendar addCalendarEntry(@RequestParam Long userId, @RequestParam Date date, @RequestParam int percentage) {
        return calendarService.addCalendarEntry(userId, date, percentage);
    }

    @GetMapping("/user/{userId}")
    public List<myCalendar> getCalendarEntries(@PathVariable Long userId) {
        return calendarService.getCalendarEntries(userId);
    }
}
