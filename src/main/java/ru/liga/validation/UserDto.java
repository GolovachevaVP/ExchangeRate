package ru.liga.validation;

public class UserDto {
    private static final int POSITION_FOR_CURRENCY = 0;
    private static final int POSITION_FOR_DATA = 1;

    public static String getCurrencyType(String input) {
        String[] currencyType = input.split("\\s+");
        String currency = currencyType[POSITION_FOR_CURRENCY];
        CurrencyValidation currVal = new CurrencyValidation();
        return currVal.validate(currency);
    }

    public static String getCurrencyForData(String input) {
        PredicateValidator predicateValidator = new PredicateValidator();
        String predicate = predicateValidator.validate(input);
        return predicate;
    }
}
