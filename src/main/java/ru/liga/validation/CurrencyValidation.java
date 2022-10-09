package ru.liga.validation;

import java.util.ArrayList;
import java.util.List;

import static ru.liga.dto.UserDto.getCurrencyType;

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
        if (!currencyList.contains(dataForCurrency.toLowerCase())){
            throw new RuntimeException("Неверный тип валюты");
        } else return dataForCurrency;
    }
}
