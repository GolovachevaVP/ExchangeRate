package ru.liga.validation;

import java.util.ArrayList;
import java.util.List;

public class PredicateValidator implements IValidation {
    private final List<String> predicateList = new ArrayList<>();

    {
        predicateList.add("tomorrow");
        predicateList.add("week");
    }

    @Override
    public String validate(String currencyForData) {
        if (!predicateList.contains(currencyForData.toLowerCase())) {
            throw new RuntimeException("Неверный диапазон прогноза");
        } else return currencyForData;
    }
}
