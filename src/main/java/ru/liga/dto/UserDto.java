package ru.liga.dto;


import ru.liga.validation.AlgorithmValidator;
import ru.liga.validation.CurrencyValidation;
import ru.liga.validation.OutputValidator;
import ru.liga.validation.PredicateValidator;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private static final int POSITION_FOR_CURRENCY = 0;
    private static final int POSITION_FOR_DATA = 1;
    private static final int POSITION_FOR_ALGOTITHM = 2;
    private static final int POSITION_FOR_OUPUT = 3;

    public static String getCurrencyType(String input) {
        String[] currencyType = input.split(" -");
        String[] currency = currencyType[POSITION_FOR_CURRENCY].split(" ");
        CurrencyValidation currVal = new CurrencyValidation();
        return currVal.validate(currency[1]);

    }

    public static List<String> numberOfCurrencies(String input) {
        List<String> numberOfCurrencyTypes =new ArrayList<>();
        String[] currencyType = input.split(" -");
        String[] currencyAndRate = currencyType[POSITION_FOR_CURRENCY].split(" ");
        String[] currency = currencyAndRate[1].split(",");
        if (currency.length > 5) {
            throw new RuntimeException("Количество валют превышает 5");
        } else {
            for (String curr : currency) {
                CurrencyValidation currVal = new CurrencyValidation();
                if (currVal.validate(curr) == "Неверный тип валюты") {
                    throw new RuntimeException("Неверный тип валюты");
                } else {
                   numberOfCurrencyTypes.add(curr);
                }
            }
        }
        return numberOfCurrencyTypes;
    }

    public static String getOutputType(String input) {
        String[] outrutType = input.split(" -");
        if (outrutType.length == 4) {
            String output = outrutType[POSITION_FOR_OUPUT];
            OutputValidator outVal = new OutputValidator();
            return outVal.validate(output);
        }
        return "";
    }

    public static String getPredicatorType(String input) {
        String[] pridicateType = input.split(" -");
        String predicate = pridicateType[POSITION_FOR_DATA];
        PredicateValidator predVal = new PredicateValidator();
        return predVal.validate(predicate);
    }

    public static String getAlgorithmType(String input) {
        String[] algorithmType = input.split(" -");
        String alg = algorithmType[POSITION_FOR_ALGOTITHM];
        AlgorithmValidator algVal = new AlgorithmValidator();
        return algVal.validate(alg);
    }
}
