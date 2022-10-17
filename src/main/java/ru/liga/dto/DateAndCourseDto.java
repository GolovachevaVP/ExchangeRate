package ru.liga.dto;

import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Value
public class DateAndCourseDto {

    double course;
    LocalDate date;

    public DateAndCourseDto(Double course, LocalDate date) {
        this.course = course;
        this.date = date;
    }
}
