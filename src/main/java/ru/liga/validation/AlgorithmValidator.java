package ru.liga.validation;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmValidator implements IValidation{
    private final List<String> algorithmList = new ArrayList<>();

    {
        algorithmList.add("alg mist");
        algorithmList.add("alg linReg");
        algorithmList.add("alg lastYear");


    }
    @Override
    public String validate(String algorithm) {
        if (!algorithmList.contains(algorithm)){
            throw new RuntimeException("Неверный алгоритм прогнозирования");
        } else return algorithm;
    }
}

