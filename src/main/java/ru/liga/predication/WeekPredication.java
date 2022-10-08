package ru.liga.predication;

import ru.liga.algorithm.IAlgorithm;
import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekPredication implements IPredication {
    public List<DateAndCourse> rate(List<DateAndCourse> course, String algorithmType) {
        LocalDate date = LocalDate.now().plusDays(7);
        List<DateAndCourse> result = new ArrayList<>();
        IAlgorithm alg = IAlgorithm.select(algorithmType);
        while (!date.equals(course.get(0).getDate())) {
            double newCourse = alg.algorithm(course, course.get(0).getDate().plusDays(1));

            DateAndCourse dateAndCourse = new DateAndCourse(newCourse, course.get(0).
                    getDate().plusDays(1));

            course.add(0, dateAndCourse);
        }
        for(int i=0; i<7; i++){
            result.add(new DateAndCourse(course.get(i).getCourse(), course.get(i).getDate()));
        }
        Collections.reverse(result);
        return result;

    }

}
