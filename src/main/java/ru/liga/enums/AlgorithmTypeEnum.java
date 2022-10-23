package ru.liga.enums;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum AlgorithmTypeEnum {

    LAST_YEAR("alg lastYear"),
    MIST("alg mist"),
    LINEAR("alg linReg");
    final String algType;

    AlgorithmTypeEnum(String algType) {
        this.algType = algType;
    }

    private static final Map<String, AlgorithmTypeEnum> roles = Arrays
            .stream(AlgorithmTypeEnum.values())
            .map(r -> new AbstractMap.SimpleEntry<>(r.algType, r))
            .collect(
                    Collectors.toMap(
                            AbstractMap.SimpleEntry::getKey,
                            AbstractMap.SimpleEntry::getValue
                    )
            );

    public static AlgorithmTypeEnum fromString(String algType) {
        AlgorithmTypeEnum algorithmTypeEnum = roles.get(algType);
        if (algorithmTypeEnum == null) {
            throw new RuntimeException("Неверный алгоритм");
        }
        return algorithmTypeEnum;
    }
}
