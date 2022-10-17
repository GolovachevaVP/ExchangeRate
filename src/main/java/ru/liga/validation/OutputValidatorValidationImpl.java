package ru.liga.validation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OutputValidatorValidationImpl implements Validation {
    private final List<String> outputList = new ArrayList<>();

    {
        outputList.add("output list");
        outputList.add("output graph");
    }

    @Override
    public String validate(String output) {
        log.debug("валидация типа вывода");
        if (!outputList.contains(output)) {
            throw new RuntimeException("Неверный тип вывода ");
        } else {
            log.debug("алгоритм отработан");
            return output;
        }
    }
}
