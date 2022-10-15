package ru.liga.algorithm;

import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourseDto;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class LastYearAlgorithmImpl implements Algorithm {

    public Double algorithm(List<DateAndCourseDto> course, LocalDate date) {
        log.debug("прогноз курса с помощью алгоритма - прошлогодний");
        log.debug("выбрана дата {}", date);
        LocalDate lastYearDate = date.minusYears(1);
        double curs;
        try {
            curs = course.stream()
                    .filter(dateAndCourseDto -> lastYearDate.equals(dateAndCourseDto.getDate()))
                    .map(DateAndCourseDto::getCourse)
                    .findFirst()
                    .orElse(course.get(1).getCourse());
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Нет значения за вчерашний день");
        }

        log.debug("алгоритм отработан");
        return curs;
    }
}

