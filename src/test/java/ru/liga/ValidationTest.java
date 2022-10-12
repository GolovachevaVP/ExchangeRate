package ru.liga;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.validation.AlgorithmValidator;
import ru.liga.validation.CurrencyValidation;
import ru.liga.validation.OutputValidator;
import ru.liga.validation.PredicateValidator;

public class ValidationTest {
    @Test
    void courseValidationTestForTheCorrectCourse(){
        CurrencyValidation currencyValidation = new CurrencyValidation();
        Assertions.assertEquals("TRY", currencyValidation.validate("TRY"));
    }

    @Test
    void courseValidationTestForTheWrongCourse(){
        CurrencyValidation currencyValidation = new CurrencyValidation();
        Assertions.assertThrows(RuntimeException.class, ()->currencyValidation.validate("PULA"));
    }

    @Test
    void predicateValidationTestForTheCorrectPredicate(){
       PredicateValidator predicateValidator = new PredicateValidator();
        Assertions.assertEquals("tomorrow", predicateValidator.validate("tomorrow"));
    }

    @Test
    void predicateValidationTestForTheWrongPredicate(){
        PredicateValidator predicateValidator = new PredicateValidator();
        Assertions.assertThrows(RuntimeException.class, ()->predicateValidator.validate("12.02.23"));
    }

    @Test
    void algorithmValidationTestForTheCorrectAlgorithm(){
        AlgorithmValidator algorithmValidator = new AlgorithmValidator();
        Assertions.assertEquals("alg linReg", algorithmValidator.validate("alg linReg"));
    }

    @Test
    void algorithmValidationTestForTheWrongAlgorithm(){
        AlgorithmValidator algorithmValidator = new AlgorithmValidator();
        Assertions.assertThrows(RuntimeException.class, ()->algorithmValidator.validate("linReg"));
    }

    @Test
    void outputValidationTestForTheCorrectOutput(){
        OutputValidator outputValidator= new OutputValidator();
        Assertions.assertEquals("output graph", outputValidator.validate("output graph"));
    }

    @Test
    void outputValidationTestForTheWrongOutput(){
        OutputValidator outputValidator= new OutputValidator();
        Assertions.assertThrows(RuntimeException.class, ()->outputValidator.validate("listt"));
    }
}
