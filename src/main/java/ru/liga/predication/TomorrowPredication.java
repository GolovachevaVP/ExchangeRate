package ru.liga.predication;

import ru.liga.algorithm.IAlgorithm;
import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


public class TomorrowPredication implements IPredication {

    public List<DateAndCourse> rate(List<DateAndCourse> course, String algorithmType) {
        double newCourse = 0;
        LocalDate date = LocalDate.now().plusDays(1);
        IAlgorithm alg = IAlgorithm.select(algorithmType);
        while (!date.equals(course.get(0).getDate())) {
            newCourse = alg.algorithm(course, course.get(0).getDate().plusDays(1));
            course.add(0, new DateAndCourse(newCourse, course.get(0).getDate().plusDays(1)));
        }
        DateAndCourse dateAndCourse = new DateAndCourse(newCourse, date);
        return Collections.singletonList(dateAndCourse);

    }


}
