package ru.liga.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PredicateValidator implements IValidation {
    private final List<String> predicateList = new ArrayList<>();
    String date;

    {
        predicateList.add("date tomorrow");
        predicateList.add(date);
        predicateList.add("period week");
        predicateList.add("period month");
    }

    @Override
    public String validate(String currencyForData) {
        date = currencyForData;
        if (!predicateList.contains(currencyForData.toLowerCase())) {
            throw new RuntimeException("Неверный диапазон прогноза");
        } else return currencyForData;
    }
}
