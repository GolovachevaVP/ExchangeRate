package ru.liga.utils;


import lombok.extern.slf4j.Slf4j;
import ru.liga.enums.AlgorithmType;
import ru.liga.validation.AlgorithmValidatorValidationImpl;
import ru.liga.validation.CurrencyValidationValidationImpl;
import ru.liga.validation.OutputValidatorValidationImpl;
import ru.liga.validation.PredicateValidatorValidationImpl;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ChekingTheEnteredRequest {
    private static final int POSITION_FOR_CURRENCY = 0;
    private static final int POSITION_FOR_DATA = 1;
    private static final int POSITION_FOR_ALGORITHM = 2;
    private static final int POSITION_FOR_OUPUT = 3;
    private static final int POSITION_FOR_ALGORITHM_TYPE = 1;

    public static List<String> searchCurrencies(String input) {
        log.debug("выделяет из запроса валюты и считает их количество");
        List<String> numberOfCurrencyTypes = new ArrayList<>();
        if (!input.contains("rate")) {
            throw new RuntimeException("Неправильно написано слово - rate");
        }
        String[] currencyType = input.split(" -");
        String currencyAndRate = currencyType[POSITION_FOR_CURRENCY].replaceAll("rate", "").replaceAll(" ", "");

        String[] currency = currencyAndRate.split(",");
        if (currency.length > 5) {
            throw new RuntimeException("Количество валют превышает 5");
        } else {
            CurrencyValidationValidationImpl currVal = new CurrencyValidationValidationImpl();
            for (String curr : currency) {
                    numberOfCurrencyTypes.add(currVal.validate(curr));
                }
            }
        log.debug("алгоритм отработан");
        return numberOfCurrencyTypes;
    }

    public static String getOutputType(String input) {
        log.debug("выделяет и проверяет тип вывода в запросе пользователя");
        String[] outrutType = input.split(" -");
        if (outrutType.length != 4 && (outrutType[POSITION_FOR_DATA].equals("period week") ||
                outrutType[POSITION_FOR_DATA].equals("period month"))) {
            throw new RuntimeException("Неверный тип вывода");
        } else if (outrutType.length == 4) {
            String output = outrutType[POSITION_FOR_OUPUT];
            OutputValidatorValidationImpl outVal = new OutputValidatorValidationImpl();
            return outVal.validate(output);
        }
        log.debug("алгоритм отработан");
        return "";
    }

    public static String getPredicatorType(String input) {
        log.debug("выделяет из запроса период прогноза");
        String[] pridicateType = input.split(" -");
        if (pridicateType.length <= 1) {
            throw new RuntimeException("Неправильный запрос");
        }
        String[] predicate = pridicateType[POSITION_FOR_DATA].split(" ");
        PredicateValidatorValidationImpl predVal = new PredicateValidatorValidationImpl();
        log.debug("алгоритм отработан");
        return predVal.validate(predicate[POSITION_FOR_DATA]);
    }

    public static AlgorithmType getAlgorithmType(String input) {
        log.debug("выделяет из запроса алгоритм прогнозирования");
        String[] algorithmType = input.split(" -");
        String[] alg = algorithmType[POSITION_FOR_ALGORITHM].split(" ");

        AlgorithmValidatorValidationImpl algVal = new AlgorithmValidatorValidationImpl();
        log.debug("алгоритм отработан");
        return AlgorithmType.valueOf(algVal.validate(alg[POSITION_FOR_ALGORITHM_TYPE]));
    }
}
