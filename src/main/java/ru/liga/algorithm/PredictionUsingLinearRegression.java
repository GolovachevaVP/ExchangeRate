package ru.liga.algorithm;

import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class PredictionUsingLinearRegression implements IAlgorithm {
    public Double algorithm(List<DateAndCourse> course, LocalDate date) {
        log.debug("прогноз курса с помощью алгоритма - линейная регрессия");
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        int count = 0;
        for (DateAndCourse dateAndCourse : course) {
            if (count <= 30) {
                x.add((double) dateAndCourse.getDate().toEpochDay());
                y.add(dateAndCourse.getCourse());
                count++;
            } else break;

        }
        double[] xArray = new double[x.size()];
        double[] yArray = new double[y.size()];
        for (int i = 0, j = 0; i < x.size(); i++, j++) {
            xArray[i] = x.get(i);
            yArray[j] = y.get(j);
        }

        LinearRegression linearRegression = new LinearRegression(xArray, yArray);
        log.debug("алгоритм отработан");
        return linearRegression.predict(date.toEpochDay());

    }


}
