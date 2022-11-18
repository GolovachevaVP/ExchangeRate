package ru.liga.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class DateAndCourseDto {

    double course;
    LocalDate date;
}
