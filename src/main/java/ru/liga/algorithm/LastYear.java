package ru.liga.algorithm;

import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.List;
@Slf4j
public class LastYear implements  IAlgorithm{

    public  Double algorithm(List<DateAndCourse> course, LocalDate date) {
        log.debug("прогноз курса с помощью алгоритма - прошлогодний");
        log.debug("выбрана дата {}", date);
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

        log.debug("алгоритм отработан");

        return curs;



    }
}

