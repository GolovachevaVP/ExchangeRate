package ru.liga.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PredicateValidator implements IValidation {
    private final List<String> predicateList = new ArrayList<>();

    {
        predicateList.add("tomorrow");
        predicateList.add("week");
        predicateList.add("month");
    }


    @Override
    public String validate(String currencyForData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        if (!predicateList.contains(currencyForData.toLowerCase())) {
            try{
                LocalDate.parse(currencyForData,formatter);
            } catch (RuntimeException e){
                throw new RuntimeException("Неверный диапазон прогноза");
            }
        }

        return currencyForData;
    }
}
