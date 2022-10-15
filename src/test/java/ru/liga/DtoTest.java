package ru.liga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.dto.DateAndCourseDto;
import ru.liga.dto.ForecastOutput;

import java.time.LocalDate;

import static ru.liga.utils.ChekingTheEnteredRequest.*;

public class DtoTest {
    @Test
    void checkingTheDateOutputInTheDesiredFormat() {
       ForecastOutput date = new ForecastOutput ();
       String res = date.localDateToString(LocalDate.of(2022, 12, 15));
       Assertions.assertEquals("чт 15.12.2022", res);
    }

    @Test
    void checkingTheDateAndCourseOutputInTheDesiredFormat() {
        ForecastOutput outputDateAndCourse = new ForecastOutput ();
        String res = outputDateAndCourse.toString(35.23357, LocalDate.of(2022, 12, 15));
        Assertions.assertEquals("чт 15.12.2022 - 35,23", res);
    }



}
