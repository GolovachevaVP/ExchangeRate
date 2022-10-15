package ru.liga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.liga.utils.ChekingTheEnteredRequest.*;
import static ru.liga.utils.ChekingTheEnteredRequest.getAlgorithmType;

public class UtilsTest {
    @Test
    void performsCheckOfTheCorrectCourse() {
        Assertions.assertEquals("TRY", searchCurrencies("rate TRY -day 23.12.2022 -alg linReg").get(0));
    }

    @Test
    void performsCheckOfSeveralCorrectCourse() {
        Assertions.assertEquals("USD",
                searchCurrencies("rate TRY,USD,AMD -day 23.12.2022 -alg linReg -output graph").get(1));
    }


    @Test
    void performsCheckOfTheIncorrectCourse() {
        Assertions.assertThrows(RuntimeException.class,
                () -> searchCurrencies("rate PULA -day 23.12.2022 -alg linReg"));
    }

    @Test
    void performsCheckOfSeveralIncorrectCourse() {
        Assertions.assertThrows(RuntimeException.class,
                () -> searchCurrencies("rate TRY,USD.AMD -day 23.12.2022 -alg linReg -output graph"));
    }

    @Test
    void performsCheckOfCorrectPredicatorType() {
        Assertions.assertEquals("23.12.2022",
                getPredicatorType("rate TRY,USD,AMD -day 23.12.2022 -alg linReg -output graph"));
    }


    @Test
    void performsCheckOfTheIncorrectPredicatorType() {
        Assertions.assertThrows(RuntimeException.class,
                () -> getPredicatorType("rate USD -day 23/12/2022 -alg linReg"));
    }

    @Test
    void performsCheckOfCorrectOutputType() {
        Assertions.assertEquals("output graph",
                getOutputType("rate TRY,USD,AMD -day 23.12.2022 -alg linReg -output graph"));
    }


    @Test
    void performsCheckOfTheIncorrectOutputType() {
        Assertions.assertThrows(RuntimeException.class,
                () -> getOutputType("rate USD -period week -alg linReg -output listе"));
    }

    @Test
    void performsCheckOfCorrectAlgorithmType() {
        Assertions.assertEquals("alg linReg",
                getAlgorithmType("rate TRY,USD,AMD -day 23.12.2022 -alg linReg -output graph"));
    }


    @Test
    void performsCheckOfTheIncorrectAlgorithmType() {
        Assertions.assertThrows(RuntimeException.class,
                () -> getAlgorithmType("rate USD -day 23.12.2022 -alg linreg"));
    }
}
