package ru.liga.utils;

import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourseDto;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
@Slf4j
public class ForecastOutput {
    static final String DATE_TIME_PATTERN = "dd.MM.yyyy";
    static final String LANGUAGE = "ru";
    static final String COUNTRY = "RU";

    public static String localDateToString(LocalDate date) {
        log.debug("перевод даты в формат dd.LL.yyyy и добавление в вывод дня недели ");
        Locale localeRu = new Locale(LANGUAGE, COUNTRY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        String formattedString = date.format(formatter);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, localeRu);
        log.debug("алгоритм отработан");
        return dayOfWeek + " " + formattedString;
    }

    public static String dateAndCourseDtoToString(DateAndCourseDto dateAndCourseDto) {
        log.debug("вывод даты и курса");
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        log.debug("алгоритм отработан");
        return localDateToString(dateAndCourseDto.getDate()) + " - " + twoDForm.format(dateAndCourseDto.getCourse());
    }
}
