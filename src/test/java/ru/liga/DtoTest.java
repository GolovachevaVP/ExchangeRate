package ru.liga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.utils.ForecastOutput;

import java.time.LocalDate;

public class DtoTest {
    @Test
    void checkingTheDateOutputInTheDesiredFormat() {
       ForecastOutput date = new ForecastOutput ();
       String res = date.localDateToString(LocalDate.of(2022, 12, 15));
       Assertions.assertEquals("чт 15.12.2022", res);
    }



}
