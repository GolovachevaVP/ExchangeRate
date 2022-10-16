package ru.liga.predication;

import lombok.extern.slf4j.Slf4j;
import ru.liga.algorithm.Algorithm;
import ru.liga.algorithm.AlgorithmFactory;
import ru.liga.dto.DateAndCourseDto;
import ru.liga.enums.AlgorithmType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Slf4j

public class FutureDatePredicationPredicationImpl implements Predication {
    String date;

    public FutureDatePredicationPredicationImpl(String date) {
        this.date = date;
    }

    public List<DateAndCourseDto> rate(List<DateAndCourseDto> course, AlgorithmType algorithmType) {
        log.debug("прогнозирует курс валюты на дату введенную пользователем");
        AlgorithmFactory algFactory = new AlgorithmFactory();
        Algorithm alg = algFactory.getAlgorithm(algorithmType);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate futureDate = LocalDate.parse(date, formatter);
        double newCourse = 0;
        while (!futureDate.equals(course.get(0).getDate())) {
            newCourse = alg.algorithm(course, course.get(0).getDate().plusDays(1));
            course.add(0, new DateAndCourseDto(newCourse, course.get(0).getDate().plusDays(1)));
        }
        DateAndCourseDto dateAndCourseDto = new DateAndCourseDto(newCourse, futureDate);
        log.debug("алгоритм отработан");
        return Collections.singletonList(dateAndCourseDto);

    }
}
