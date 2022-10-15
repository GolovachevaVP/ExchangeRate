package ru.liga.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
@Slf4j
public class ForecastOutput {

    public String localDateToString(LocalDate date) {
        log.debug("перевод даты в формат dd.LL.yyyy и добавление в вывод дня недели ");
        Locale localeRu = new Locale("ru", "RU");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedString = date.format(formatter);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, localeRu);
        log.debug("алгоритм отработан");
        return dayOfWeek + " " + formattedString;
    }

    public String toString(Double course, LocalDate date) {
        log.debug("вывод даты и курса");
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        log.debug("алгоритм отработан");
        return localDateToString(date) + " - " + twoDForm.format(course);
    }
}
