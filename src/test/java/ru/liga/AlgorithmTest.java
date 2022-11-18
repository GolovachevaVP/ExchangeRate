package ru.liga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.algorithm.LastYearAlgorithmImpl;
import ru.liga.algorithm.LinearAlgorithmImpl;
import ru.liga.dto.DateAndCourseDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmTest {
    @Test
    void checksTheWorkOfAlgLastYearWithTheCourseForLastYear() {
        LastYearAlgorithmImpl courseForLastYearAlgorithmImpl = new LastYearAlgorithmImpl();
        Assertions.assertEquals(3.0,
                courseForLastYearAlgorithmImpl.algorithm(currecyList, LocalDate.of(2022, 10, 5)));
    }

    @Test
    void checksTheWorkOfAlgLastYearWithoutTheCourseForLastYear() {
        LastYearAlgorithmImpl courseForLastYearAlgorithmImpl = new LastYearAlgorithmImpl();
        Assertions.assertEquals(5.0,
                courseForLastYearAlgorithmImpl.algorithm(currecyList, LocalDate.of(2022, 10, 3)));
    }

    @Test
    void checksTheWorkOfAlgLinReg() {
        LinearAlgorithmImpl cursLinReg = new LinearAlgorithmImpl();
        Assertions.assertEquals(5.005524861878442,
                cursLinReg.algorithm(currecyList, LocalDate.of(2022, 10, 3)));
    }


    List<DateAndCourseDto> currecyList = new ArrayList();

    {

        currecyList.add(new DateAndCourseDto(3.0, LocalDate.of(2021, 10, 5)));
        currecyList.add(new DateAndCourseDto(5.0, LocalDate.of(2022, 10, 2)));


    }
}
