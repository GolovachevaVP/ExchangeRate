package ru.liga.predication;

import ru.liga.algorithm.IAlgorithm;
import ru.liga.dto.DateAndCourse;

import java.util.List;
import java.util.concurrent.Future;

public interface IPredication {


    public List<DateAndCourse> rate(List<DateAndCourse> course, String algorithmType);

    public static IPredication select(String type) {
        if (type.equals("week")) {
            return new WeekPredication();
        } else if (type.equals("month")) {
            return new MonthPredication();
        } else if (type.equals("tomorrow")) {
            return new TomorrowPredication();
        } else {
            return new FutureDatePredication(type);
        }
    }
}
