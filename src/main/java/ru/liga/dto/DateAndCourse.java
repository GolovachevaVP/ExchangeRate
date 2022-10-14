package ru.liga.dto;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
@Slf4j
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
        log.debug("перевод даты в формат dd.LL.yyyy и добавление в вывод дня недели ");
        Locale localeRu = new Locale("ru", "RU");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy");
        String formattedString = date.format(formatter);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, localeRu);
        log.debug("алгоритм отработан");
        return dayOfWeek + " " + formattedString;
    }

    public String toString(String currencyType){
        log.debug("вывод даты и курса");
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        log.debug("алгоритм отработан");
        return localDateToString(this.date)+" - "+twoDForm.format(this.course);
    }

}
