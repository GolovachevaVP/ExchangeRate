package ru.liga.validation;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;
@Slf4j

public class AlgorithmValidator implements IValidation{
    private final List<String> algorithmList = new ArrayList<>();

    {
        algorithmList.add("alg mist");
        algorithmList.add("alg linReg");
        algorithmList.add("alg lastYear");


    }
    @Override
    public String validate(String algorithm) {
        log.debug("валидация алгоритма прогнозирования");
        if (!algorithmList.contains(algorithm)){
            throw new RuntimeException("Неверный алгоритм прогнозирования");

        }else {
            log.debug("алгоритм отработан");
            return algorithm;
        }

    }
}

