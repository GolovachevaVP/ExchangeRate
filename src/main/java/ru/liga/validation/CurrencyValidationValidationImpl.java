package ru.liga.validation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j

public class CurrencyValidationValidationImpl implements Validation {
    private static final int POSITION_FOR_CURRENCY = 0;
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
            for (String curr : currency) {
                CurrencyValidationValidationImpl currVal = new CurrencyValidationValidationImpl();
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

    private final List<String> currencyList = new ArrayList<>();

    {
        currencyList.add("eur");
        currencyList.add("usd");
        currencyList.add("try");
        currencyList.add("bgn");
        currencyList.add("amd");


    }

    @Override
    public String validate(String dataForCurrency) {
        log.debug("валидация типа валюты");
        if (!currencyList.contains(dataForCurrency.toLowerCase())) {
            throw new RuntimeException("Неверный тип валюты");
        } else {
            log.debug("алгоритм отработан");
            return dataForCurrency;
        }
    }
}
