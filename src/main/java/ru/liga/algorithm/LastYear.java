package ru.liga.algorithm;

import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.List;

public class LastYear implements  IAlgorithm{

    public  Double algorithm(List<DateAndCourse> course, LocalDate date) {
        date = date.minusYears(1);
        double curs = 0;
        for (DateAndCourse dateFromThelist : course) {
            if (date.equals(dateFromThelist.getDate())) {
                curs = dateFromThelist.getCourse();
                break;
            }
        }
        if (curs == 0) {
            curs = course.get(1).getCourse();
        }
        return curs;

    }
}

