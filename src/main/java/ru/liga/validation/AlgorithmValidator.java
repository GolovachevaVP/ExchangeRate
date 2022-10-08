package ru.liga.validation;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmValidator implements IValidation{
    private final List<String> algorithmList = new ArrayList<>();

    {
        algorithmList.add("mist");
        algorithmList.add("linReg");
        algorithmList.add("lastYear");


    }
    @Override
    public String validate(String algorithm) {
        if (!algorithmList.contains(algorithm)){
            throw new RuntimeException("Неверный алгоритм прогнозирования");
        } else return algorithm;
    }
}

