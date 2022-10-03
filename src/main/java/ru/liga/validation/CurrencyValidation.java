package ru.liga.validation;

import java.util.ArrayList;
import java.util.List;

public class CurrencyValidation implements IValidation {

    private final List<String> currencyList = new ArrayList<>();

    {
        currencyList.add("EUR");
        currencyList.add("USD");
        currencyList.add("TRY");

    }
    @Override
    public String validate(String input) {
        String[] currencyType = input.split("\\s+");
        String dataForCurrency = currencyType[0];
        if (!currencyList.contains(dataForCurrency.toUpperCase())){
            throw new RuntimeException("Неверный тип валюты");
        } else return dataForCurrency;
    }
}