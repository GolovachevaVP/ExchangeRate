package ru.liga.enums;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum OutputType {

    GRAPH("output graph"),
    LIST("output list"),
    WITHOUT_OUTPUT_TYPE("");
    final String outputType;

    OutputType(String outputType) {
        this.outputType = outputType;
    }

    private static final Map<String, OutputType> roles = Arrays
            .stream(OutputType.values())
            .map(r -> new AbstractMap.SimpleEntry<>(r.outputType, r))
            .collect(
                    Collectors.toMap(
                            AbstractMap.SimpleEntry::getKey,
                            AbstractMap.SimpleEntry::getValue
                    )
            );

    public static OutputType fromString(String outputType) {
        OutputType type = roles.get(outputType);
        if (type == null) {
            throw new RuntimeException("Неверный тип вывода");
        }
        return type;
    }
}
