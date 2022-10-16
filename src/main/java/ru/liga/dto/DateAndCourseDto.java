package ru.liga.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Slf4j  @Getter
public class DateAndCourseDto {

    private double course;
    private LocalDate date;

    public DateAndCourseDto(Double course, LocalDate date) {
        this.course = course;
        this.date = date;
    }

    public String toString(){
        ForecastOutput forecastOutput = new ForecastOutput();
        String result = forecastOutput.toString(course,date);
        return result;
    }
}
