package ru.liga.predication;

import ru.liga.DateAndCourse;

import java.util.List;

public interface IPredication {


    public List<DateAndCourse> rate(List<DateAndCourse> course);

    public static IPredication select(String type) {
        if (type.equals("week")) {
            return new WeekPredication();
        } else {
            return new TomorrowPredication();
        }
    }

}
