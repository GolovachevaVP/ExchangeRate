package ru.liga;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.validation.AlgorithmValidatorValidationImpl;
import ru.liga.validation.CurrencyValidationValidationImpl;
import ru.liga.validation.OutputValidatorValidationImpl;
import ru.liga.validation.PredicateValidatorValidationImpl;

public class ValidationTest {
    @Test
    void courseValidationTestForTheCorrectCourse() {
        CurrencyValidationValidationImpl currencyValidationValidationImpl = new CurrencyValidationValidationImpl();
        Assertions.assertEquals("TRY", currencyValidationValidationImpl.validate("TRY"));
    }

    @Test
    void courseValidationTestForTheWrongCourse() {
        CurrencyValidationValidationImpl currencyValidationValidationImpl = new CurrencyValidationValidationImpl();
        Assertions.assertThrows(RuntimeException.class, () -> currencyValidationValidationImpl.validate("PULA"));
    }

    @Test
    void predicateValidationTestForTheCorrectPredicate() {
        PredicateValidatorValidationImpl predicateValidatorValidationImpl = new PredicateValidatorValidationImpl();
        Assertions.assertEquals("tomorrow", predicateValidatorValidationImpl.validate("tomorrow"));
    }

    @Test
    void predicateValidationTestForTheWrongPredicate() {
        PredicateValidatorValidationImpl predicateValidatorValidationImpl = new PredicateValidatorValidationImpl();
        Assertions.assertThrows(RuntimeException.class, () -> predicateValidatorValidationImpl.validate("12.02.23"));
    }

    @Test
    void algorithmValidationTestForTheCorrectAlgorithm() {
        AlgorithmValidatorValidationImpl algorithmValidatorValidationImpl = new AlgorithmValidatorValidationImpl();
        Assertions.assertEquals("alg linReg", algorithmValidatorValidationImpl.validate("alg linReg"));
    }

    @Test
    void algorithmValidationTestForTheWrongAlgorithm() {
        AlgorithmValidatorValidationImpl algorithmValidatorValidationImpl = new AlgorithmValidatorValidationImpl();
        Assertions.assertThrows(RuntimeException.class, () -> algorithmValidatorValidationImpl.validate("linReg"));
    }

    @Test
    void outputValidationTestForTheCorrectOutput() {
        OutputValidatorValidationImpl outputValidatorValidationImpl = new OutputValidatorValidationImpl();
        Assertions.assertEquals("output graph", outputValidatorValidationImpl.validate("output graph"));
    }

    @Test
    void outputValidationTestForTheWrongOutput() {
        OutputValidatorValidationImpl outputValidatorValidationImpl = new OutputValidatorValidationImpl();
        Assertions.assertThrows(RuntimeException.class, () -> outputValidatorValidationImpl.validate("listt"));
    }
}
