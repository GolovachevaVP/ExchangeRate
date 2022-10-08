package ru.liga.dto;

import ru.liga.validation.AlgorithmValidator;
import ru.liga.validation.CurrencyValidation;
import ru.liga.validation.PredicateValidator;

public class UserDto {
    private static final int POSITION_FOR_CURRENCY = 0;
    private static final int POSITION_FOR_DATA = 1;

    private static final int POSITION_FOR_ALGOTITHM= 2;

    public static String getCurrencyType(String input) {
        String[] currencyType = input.split("\\s+");
        String currency = currencyType[POSITION_FOR_CURRENCY];
        CurrencyValidation currVal = new CurrencyValidation();
        return currVal.validate(currency);
    }

    public static String getPredicatorType(String input) {
        String[] dataType = input.split("\\s+");
        String predicate= dataType[POSITION_FOR_DATA];
        PredicateValidator predVal = new PredicateValidator();
        return predVal.validate(predicate);
    }

    public static String getAlgorithmType(String input) {
        String[] dataType = input.split("\\s+");
        String alg= dataType[POSITION_FOR_ALGOTITHM];
        AlgorithmValidator algVal = new AlgorithmValidator();
        return algVal.validate(alg);
    }
}
