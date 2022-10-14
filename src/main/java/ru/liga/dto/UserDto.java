package ru.liga.dto;


import lombok.extern.slf4j.Slf4j;
import ru.liga.validation.AlgorithmValidator;
import ru.liga.validation.CurrencyValidation;
import ru.liga.validation.OutputValidator;
import ru.liga.validation.PredicateValidator;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class UserDto {
    private static final int POSITION_FOR_CURRENCY = 0;
    private static final int POSITION_FOR_DATA = 1;
    private static final int POSITION_FOR_ALGOTITHM = 2;
    private static final int POSITION_FOR_OUPUT = 3;


    public static List<String> searchCurrencies(String input) {
        log.debug("выделяет из запроса валюты и считает их количество");
        List<String> numberOfCurrencyTypes =new ArrayList<>();
        if(!input.contains("rate")){
            throw new RuntimeException("Неправильно написано слово - rate");
        }
        String[] currencyType = input.split(" -");
        String currencyAndRate = currencyType[POSITION_FOR_CURRENCY].replaceAll("rate","").replaceAll(" ","");

        String[] currency = currencyAndRate.split(",");
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
        log.debug("алгоритм отработан");
        return numberOfCurrencyTypes;
    }

    public static String getOutputType(String input) {
        log.debug("выделяет и проверяет тип вывода в запросе пользователя");
        String[] outrutType = input.split(" -");
        if (outrutType.length != 4 && (outrutType[POSITION_FOR_DATA].equals("period week") ||
                outrutType[POSITION_FOR_DATA].equals("period month"))) {
            throw new RuntimeException("Неверный тип вывода");
        }else if (outrutType.length == 4){
            String output = outrutType[POSITION_FOR_OUPUT];
            OutputValidator outVal = new OutputValidator();
            return outVal.validate(output);
        }
        log.debug("алгоритм отработан");
        return "";
    }

    public static String getPredicatorType(String input) {
        log.debug("выделяет из запроса период прогноза");
        String[] pridicateType = input.split(" -");
        if(pridicateType.length<=1) {
            throw new RuntimeException("Неправильный запрос");
        }
        String[] predicate = pridicateType[POSITION_FOR_DATA].split(" ");
        PredicateValidator predVal = new PredicateValidator();
        log.debug("алгоритм отработан");
        return predVal.validate(predicate[POSITION_FOR_DATA]);
    }

    public static String getAlgorithmType(String input) {
        log.debug("выделяет из запроса алгоритм прогнозирования");
        String[] algorithmType = input.split(" -");
        String alg = algorithmType[POSITION_FOR_ALGOTITHM];
        AlgorithmValidator algVal = new AlgorithmValidator();
        log.debug("алгоритм отработан");
        return algVal.validate(alg);
    }
}
