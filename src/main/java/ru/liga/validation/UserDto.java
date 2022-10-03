package ru.liga.validation;

public class UserDto {
    private static final int POSITION_FOR_CURRENCY = 0;
    private static final int POSITION_FOR_DATA = 1;

    public static String getDataForCurrency(String input) {
        CurrencyValidation currencyValidationc = new CurrencyValidation();
        String currency = currencyValidationc.validate(input);
        return currency;
    }

    public static String getCurrencyForData(String input) {
        PredicateValidator predicateValidator = new PredicateValidator();
        String predicate = predicateValidator.validate(input);
        return predicate;
    }
}
