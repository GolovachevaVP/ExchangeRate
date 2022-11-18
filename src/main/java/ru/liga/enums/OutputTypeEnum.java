package ru.liga.enums;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum OutputTypeEnum {

    GRAPH("output graph"),
    LIST("output list"),
    WITHOUT_OUTPUT_TYPE("");
    final String outputType;

    OutputTypeEnum(String outputType) {
        this.outputType = outputType;
    }

    private static final Map<String, OutputTypeEnum> roles = Arrays
            .stream(OutputTypeEnum.values())
            .map(r -> new AbstractMap.SimpleEntry<>(r.outputType, r))
            .collect(
                    Collectors.toMap(
                            AbstractMap.SimpleEntry::getKey,
                            AbstractMap.SimpleEntry::getValue
                    )
            );

    public static OutputTypeEnum fromString(String outputType) {
        OutputTypeEnum type = roles.get(outputType);
        if (type == null) {
            throw new RuntimeException("Неверный тип вывода");
        }
        return type;
    }
}
