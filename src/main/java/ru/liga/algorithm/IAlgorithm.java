package ru.liga.algorithm;


import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.List;

public interface IAlgorithm {

    public Double algorithm(List<DateAndCourse> course, LocalDate date) ;
    public static IAlgorithm select(String type) {
        if (type.equals("lastYear")) {
            return new LastYear();
        } else if (type.equals("mist")) {
            return new Mystical();
        } else{
            return new predictionUsingLinearRegression();
        }
    }

}
