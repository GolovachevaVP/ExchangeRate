package ru.liga.utils;


import lombok.extern.slf4j.Slf4j;
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
    private static final int POSITION_FOR_ALGOTITHM = 2;
    private static final int POSITION_FOR_OUPUT = 3;




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

    public static String getAlgorithmType(String input) {
        log.debug("выделяет из запроса алгоритм прогнозирования");
        String[] algorithmType = input.split(" -");
        String alg = algorithmType[POSITION_FOR_ALGOTITHM];
        AlgorithmValidatorValidationImpl algVal = new AlgorithmValidatorValidationImpl();
        log.debug("алгоритм отработан");
        return algVal.validate(alg);
    }
}
