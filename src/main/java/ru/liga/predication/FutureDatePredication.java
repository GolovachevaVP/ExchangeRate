package ru.liga.predication;

import lombok.extern.slf4j.Slf4j;
import ru.liga.algorithm.IAlgorithm;
import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
@Slf4j

public class FutureDatePredication implements IPredication {
    String date;

    public FutureDatePredication(String date) {
        this.date = date;
    }

    public List<DateAndCourse> rate(List<DateAndCourse> course, String algorithmType) {
        log.debug("прогнозирует курс валюты на дату введенную пользователем");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate futureDate = LocalDate.parse(date,formatter);
        double newCourse = 0;
        IAlgorithm alg = IAlgorithm.select(algorithmType);
        while (!futureDate.equals(course.get(0).getDate())) {
            newCourse = alg.algorithm(course, course.get(0).getDate().plusDays(1));
            course.add(0, new DateAndCourse(newCourse, course.get(0).getDate().plusDays(1)));
        }
        DateAndCourse dateAndCourse = new DateAndCourse(newCourse, futureDate);
        log.debug("алгоритм отработан");
        return Collections.singletonList(dateAndCourse);

    }
}
