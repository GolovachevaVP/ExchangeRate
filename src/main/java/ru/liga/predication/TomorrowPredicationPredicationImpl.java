package ru.liga.predication;

import lombok.extern.slf4j.Slf4j;
import ru.liga.algorithm.Algorithm;
import ru.liga.algorithm.AlgorithmFactory;
import ru.liga.dto.DateAndCourseDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
public class TomorrowPredicationPredicationImpl implements Predication {
    public List<DateAndCourseDto> rate(List<DateAndCourseDto> course, String algorithmType) {
        log.debug("прогнозирует курс валюты на завтрашний день");
        double newCourse = 0;
        LocalDate date = LocalDate.now().plusDays(1);
        AlgorithmFactory algFactory = new AlgorithmFactory();
        Algorithm alg = algFactory.getAlgorithm(algorithmType);
        while (!date.equals(course.get(0).getDate())) {
            newCourse = alg.algorithm(course, course.get(0).getDate().plusDays(1));
            course.add(0, new DateAndCourseDto(newCourse, course.get(0).getDate().plusDays(1)));
        }
        DateAndCourseDto dateAndCourseDto = new DateAndCourseDto(newCourse, date);
        log.debug("алгоритм отработан");
        return Collections.singletonList(dateAndCourseDto);
    }
}
