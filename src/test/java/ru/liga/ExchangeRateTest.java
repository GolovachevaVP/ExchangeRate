package ru.liga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static ru.liga.ExchangeRate.invoke;

public class ExchangeRateTest {
    @Test
    void checkingTheExecutionOfTheUserRequest() throws IOException {
        Assertions.assertEquals("пт 23.12.2022 - 25,28", invoke("rate TRY -day 23.12.2022 -alg linReg").get(0));
    }


    @Test
    void checkingForAnIncorrectUserRequest() {
        Assertions.assertThrows(RuntimeException.class, () -> invoke("rate USD -day 23.12.2022 -alg linReg -output graph"));
    }

}
