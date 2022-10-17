package ru.liga;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.validation.PredicateValidatorValidationImpl;

public class ValidationTest {

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

}
