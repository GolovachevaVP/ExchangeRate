package ru.liga.algorithm;

import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
@Slf4j
public class Mystical implements IAlgorithm {
    public Double algorithm(List<DateAndCourse> course, LocalDate date) {
        log.debug("прогноз курса с помощью алгоритма - мистический");
        LocalDate start = LocalDate.of(2005,01,01);
        long years = ChronoUnit.YEARS.between(start, date);
        LocalDate randomDate = date.minusYears(new Random().nextInt((int)years+1));
        double curs = 0;
        for (DateAndCourse dateFromThelist : course) {
            if (randomDate.equals(dateFromThelist.getDate())) {
                curs = dateFromThelist.getCourse();
                break;
            }
        }
        if (curs == 0) {
            curs = course.get(1).getCourse();
        }
        log.debug("алгоритм отработан");
        return curs;
    }
}
