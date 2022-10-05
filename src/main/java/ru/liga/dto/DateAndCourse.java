package ru.liga.dto;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateAndCourse {
    private double course;
    private LocalDate date;


    public Double getCourse() {
        return this.course;
    }
    public LocalDate getDate() {
        return this.date;
    }

    public DateAndCourse(Double course, LocalDate date) {
        this.course = course;
        this.date = date;
    }

    public String localDateToString(LocalDate date) {
        Locale localeRu = new Locale("ru", "RU");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy");
        String formattedString = date.format(formatter);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, localeRu);
        return dayOfWeek + " " + formattedString;
    }

    public String toString(String currencyType){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return "rate "+currencyType+" "+localDateToString(this.date)+" - "+twoDForm.format(this.course);
    }
}
