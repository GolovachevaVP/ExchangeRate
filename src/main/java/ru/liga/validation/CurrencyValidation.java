package ru.liga.validation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j

public class CurrencyValidation implements IValidation {

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
        if (!currencyList.contains(dataForCurrency.toLowerCase())){
            throw new RuntimeException("Неверный тип валюты");
        } else{
            log.debug("алгоритм отработан");
            return dataForCurrency;
        }
    }
}
