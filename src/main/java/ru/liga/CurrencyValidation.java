package ru.liga;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyValidation implements IValidation {

    private final List<String> currencyList = new ArrayList<>();

    {
        currencyList.add("EUR");
        currencyList.add("USD");
        currencyList.add("TRY");

    }
    @Override
    public  String validate(String input) {
        ValidationDto validationDto = new ValidationDto();
        String dataForCurrency = validationDto.getDataForCurrency(input);
        if (!currencyList.contains(dataForCurrency.toUpperCase())){
            throw new RuntimeException("Неверный тип валюты");
        } else return dataForCurrency;
    }
}