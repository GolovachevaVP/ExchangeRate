package ru.liga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.algorithm.LastYear;
import ru.liga.algorithm.PredictionUsingLinearRegression;
import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmTest {
    @Test
    void checksTheWorkOfAlgLastYearWithTheCourseForLastYear() {
        LastYear courseForLastYear = new LastYear();
        Assertions.assertEquals(3.0,
                courseForLastYear.algorithm(currecyList,LocalDate.of(2022,10,5)));
    }

    @Test
    void checksTheWorkOfAlgLastYearWithoutTheCourseForLastYear() {
        LastYear courseForLastYear = new LastYear();
        Assertions.assertEquals(5.0,
                courseForLastYear.algorithm(currecyList,LocalDate.of(2022,10,3)));
    }

    @Test
    void checksTheWorkOfAlgLinReg() {
        PredictionUsingLinearRegression cursLinReg =  new PredictionUsingLinearRegression();
        Assertions.assertEquals(5.005524861878442,
                cursLinReg.algorithm(currecyList,LocalDate.of(2022,10,3)));
    }


    List<DateAndCourse> currecyList = new ArrayList();

    {

        currecyList.add(new DateAndCourse(3.0, LocalDate.of(2021, 10, 5)));
        currecyList.add(new DateAndCourse(5.0, LocalDate.of(2022, 10, 2)));


    }
}
