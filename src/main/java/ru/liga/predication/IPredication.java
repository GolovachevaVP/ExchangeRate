package ru.liga.predication;

import ru.liga.DateAndCourse;

import java.util.List;

public interface IPredication {


    public List<String> rate(List<DateAndCourse> course, String currencyType);

    public static IPredication select(String type) {
        if (type.equals("week")) {
            return new WeekPredication();
        } else {
            return new TomorrowPredication();
        }
    }

}
