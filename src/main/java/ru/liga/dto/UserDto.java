package ru.liga.dto;

import ru.liga.validation.CurrencyValidation;
import ru.liga.validation.PredicateValidator;

public class UserDto {
    private static final int POSITION_FOR_CURRENCY = 0;
    private static final int POSITION_FOR_DATA = 1;

    public static String getCurrencyType(String input) {
        String[] currencyType = input.split("\\s+");
        String currency = currencyType[POSITION_FOR_CURRENCY];
        CurrencyValidation currVal = new CurrencyValidation();
        return currVal.validate(currency);
    }

    public static String getPredicatorType(String uuu) {
        String[] dataType1 = uuu.split("\\s+");
        String predicate= dataType1[POSITION_FOR_DATA];
        PredicateValidator predVal = new PredicateValidator();
        return predVal.validate(predicate);
    }




}
