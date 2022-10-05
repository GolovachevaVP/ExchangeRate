package ru.liga.validation;

import java.util.ArrayList;
import java.util.List;

import static ru.liga.dto.UserDto.getCurrencyType;

public class CurrencyValidation implements IValidation {

    private final List<String> currencyList = new ArrayList<>();

    {
        currencyList.add("EUR");
        currencyList.add("USD");
        currencyList.add("TRY");

    }
    @Override
    public String validate(String dataForCurrency) {
        if (!currencyList.contains(dataForCurrency.toUpperCase())){
            throw new RuntimeException("Неверный тип валюты");
        } else return dataForCurrency;
    }
}
