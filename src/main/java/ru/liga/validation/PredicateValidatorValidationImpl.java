package ru.liga.validation;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PredicateValidatorValidationImpl implements Validation {
    private final List<String> predicateList = new ArrayList<>();

    {
        predicateList.add("tomorrow");
        predicateList.add("week");
        predicateList.add("month");
    }


    @Override
    public String validate(String currencyForData) {
        log.debug("валидация периода прогноза");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        if (!predicateList.contains(currencyForData.toLowerCase())) {
            try {
                LocalDate.parse(currencyForData, formatter);
            } catch (RuntimeException e) {
                throw new RuntimeException("Неверный период прогноза");
            }
        }
        log.debug("алгоритм отработан");
        return currencyForData;
    }
}
