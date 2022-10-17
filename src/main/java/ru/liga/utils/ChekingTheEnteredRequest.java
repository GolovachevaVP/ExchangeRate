package ru.liga.utils;


import lombok.extern.slf4j.Slf4j;
import ru.liga.enums.AlgorithmType;
import ru.liga.enums.CurrencyType;
import ru.liga.enums.OutputType;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ChekingTheEnteredRequest {
    private static final int POSITION_FOR_CURRENCY = 0;
    private static final int POSITION_FOR_DATA = 1;
    private static final int POSITION_FOR_ALGORITHM = 2;
    private static final int POSITION_FOR_OUPUT = 3;

    public static List<CurrencyType> searchCurrencies(String input) {
        log.debug("выделяет из запроса валюты и считает их количество");
        List<CurrencyType> currencyTypes = new ArrayList<>();
        if (!input.contains("rate")) {
            throw new RuntimeException("Неправильно написано слово - rate");
        }
        String[] currencyType = input.split(" -");
        String currencyAndRate = currencyType[POSITION_FOR_CURRENCY].replaceAll("rate", "").replaceAll(" ", "");

        String[] currency = currencyAndRate.split(",");
        if (currency.length > 5) {
            throw new RuntimeException("Количество валют превышает 5");
        } else {
            for (String curr : currency) {
                currencyTypes.add(CurrencyType.fromString(curr));
            }
        }
        log.debug("алгоритм отработан");
        return currencyTypes;
    }

    public static OutputType getOutputType(String input) {
        log.debug("выделяет и проверяет тип вывода в запросе пользователя");
        String[] outputType = input.split(" -");
        if (outputType.length != 4 && (outputType[POSITION_FOR_DATA].equals("period week") ||
                outputType[POSITION_FOR_DATA].equals("period month"))) {
            throw new RuntimeException("Неверный тип вывода");
        } else if (outputType.length == 4) {
            String output = outputType[POSITION_FOR_OUPUT];
            return OutputType.fromString(output);
        }
        log.debug("алгоритм отработан");
        return OutputType.WITHOUT_OUTPUT_TYPE;
    }

    public static String getPredicatorType(String input) {
        log.debug("выделяет из запроса период прогноза");
        String[] pridicateType = input.split(" -");
        if (pridicateType.length <= 1) {
            throw new RuntimeException("Неправильный запрос");
        }
        String[] predicate = pridicateType[POSITION_FOR_DATA].split(" ");
        log.debug("алгоритм отработан");
        return predicate[POSITION_FOR_DATA];
    }

    public static AlgorithmType getAlgorithmType(String input) {
        log.debug("выделяет из запроса алгоритм прогнозирования");
        String[] partOfAlgorithmType = input.split(" -");
        String alg = partOfAlgorithmType[POSITION_FOR_ALGORITHM];
        log.debug("алгоритм отработан");
        return AlgorithmType.fromString(alg);
    }
}
