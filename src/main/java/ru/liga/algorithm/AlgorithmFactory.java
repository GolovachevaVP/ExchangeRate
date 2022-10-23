package ru.liga.algorithm;

import ru.liga.enums.AlgorithmTypeEnum;

public class AlgorithmFactory {

    public Algorithm getAlgorithm(AlgorithmTypeEnum type) {
        switch (type) {
            case LAST_YEAR:
                return new LastYearAlgorithmImpl();
            case LINEAR:
                return new LinearAlgorithmImpl();
            default:
                return new MysticalAlgorithmImpl();
        }
    }
}
