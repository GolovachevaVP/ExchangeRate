package ru.liga.algorithm;

import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourseDto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Slf4j
public class MysticalAlgorithmImpl implements Algorithm {
    public Double algorithm(List<DateAndCourseDto> course, LocalDate date) {
        log.debug("прогноз курса с помощью алгоритма - мистический");
        LocalDate start = LocalDate.of(2005, 01, 01);
        long years = ChronoUnit.YEARS.between(start, date);
        LocalDate randomDate = date.minusYears(new Random().nextInt((int) years + 1));
        double curs = course.stream()
                .filter(dateAndCourseDto -> randomDate.equals(dateAndCourseDto.getDate()))
                .map(DateAndCourseDto::getCourse)
                .findFirst()
                .orElse(new DateAndCourseDto(0.0, null).getCourse());
        if (curs == 0) {
            if (course.size() >= 2) {
                curs = course.get(1).getCourse();
            } else {
                throw new RuntimeException("Нет значение за вчерашний день");
            }
        }
        log.debug("алгоритм отработан");
        return curs;
    }
}
