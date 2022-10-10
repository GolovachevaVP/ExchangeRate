package ru.liga.predication;

import ru.liga.algorithm.IAlgorithm;
import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class FutureDatePredication implements IPredication {
    String date;

    public FutureDatePredication(String date) {
        this.date = date;
    }


    public List<DateAndCourse> rate(List<DateAndCourse> course, String algorithmType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate futureDate = LocalDate.parse(date,formatter);
        double newCourse = 0;
        IAlgorithm alg = IAlgorithm.select(algorithmType);
        while (!futureDate.equals(course.get(0).getDate())) {
            newCourse = alg.algorithm(course, course.get(0).getDate().plusDays(1));
            course.add(0, new DateAndCourse(newCourse, course.get(0).getDate().plusDays(1)));
        }
        DateAndCourse dateAndCourse = new DateAndCourse(newCourse, futureDate);
        return Collections.singletonList(dateAndCourse);

    }
}
