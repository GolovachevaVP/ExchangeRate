package ru.liga.predication;

import lombok.extern.slf4j.Slf4j;
import ru.liga.algorithm.Algorithm;
import ru.liga.algorithm.AlgorithmFactory;
import ru.liga.dto.DateAndCourseDto;
import ru.liga.enums.AlgorithmType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class MonthPredicationPredicationImpl implements Predication {
    public List<DateAndCourseDto> rate(List<DateAndCourseDto> course, AlgorithmType algorithmType) {
        log.debug("прогнозирует курс валюты на месяц");
        LocalDate date = LocalDate.now().plusDays(30);
        List<DateAndCourseDto> result = new ArrayList<>();
        AlgorithmFactory algFactory = new AlgorithmFactory();
        Algorithm alg = algFactory.getAlgorithm(algorithmType);
        while (!date.equals(course.get(0).getDate())) {
            double newCourse = alg.algorithm(course, course.get(0).getDate().plusDays(1));

            DateAndCourseDto dateAndCourseDto = new DateAndCourseDto(newCourse, course.get(0).
                    getDate().plusDays(1));

            course.add(0, dateAndCourseDto);
        }
        for (int i = 0; i < 30; i++) {
            result.add(new DateAndCourseDto(course.get(i).getCourse(), course.get(i).getDate()));
        }
        Collections.reverse(result);
        log.debug("алгоритм отработан");
        return result;
    }
}
