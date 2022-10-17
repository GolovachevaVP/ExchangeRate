package ru.liga.enums;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum AlgorithmType {

    LAST_YEAR("alg lastYear"),
    MIST("alg mist"),
    LINEAR("alg linReg");
    final String algType;

    AlgorithmType(String algType) {
        this.algType = algType;
    }

    private static final Map<String, AlgorithmType> roles = Arrays
            .stream(AlgorithmType.values())
            .map(r -> new AbstractMap.SimpleEntry<>(r.algType, r))
            .collect(
                    Collectors.toMap(
                            AbstractMap.SimpleEntry::getKey,
                            AbstractMap.SimpleEntry::getValue
                    )
            );

    public static AlgorithmType fromString(String algType) {
        AlgorithmType algorithmType = roles.get(algType);
        if (algorithmType == null) {
            throw new RuntimeException("Неверный алгоритм");
        }
        return algorithmType;
    }
}
