package ru.liga.validation;

import java.util.ArrayList;
import java.util.List;

public class OutputValidator implements IValidation {
    private final List<String> outputList = new ArrayList<>();

    {
        outputList.add("output list");
        outputList.add("output graph");

    }
    @Override
    public String validate(String output) {
        if (!outputList.contains(output)){
            throw new RuntimeException("Неверный тип вывода ");
        } else return output;
    }
}
