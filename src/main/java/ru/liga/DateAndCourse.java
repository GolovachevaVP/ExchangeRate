package ru.liga;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateAndCourse {
    private double course;
    private LocalDate getDate;

    public Double getCourse() {
        return this.course;
    }
    public LocalDate getDate() {
        return this.getDate;
    }

    public DateAndCourse(Double course, LocalDate date) {
        this.course = course;
        this.getDate = date;
    }

    public static String localDateToString(LocalDate date) {
        Locale localeRu = new Locale("ru", "RU");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy");
        String formattedString = date.format(formatter);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, localeRu);
        return dayOfWeek + " " + formattedString;
    }
}
