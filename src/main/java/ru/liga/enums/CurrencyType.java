package ru.liga.enums;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CurrencyType {

    USD("USD"),
    EUR("EUR"),
    AMD("AMD"),
    BGN("BGN"),
    TRY("TRY");
    final String currencyType;

    CurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    private static final Map<String, CurrencyType> roles = Arrays
            .stream(CurrencyType.values())
            .map(r -> new AbstractMap.SimpleEntry<>(r.currencyType, r))
            .collect(
                    Collectors.toMap(
                            AbstractMap.SimpleEntry::getKey,
                            AbstractMap.SimpleEntry::getValue
                    )
            );

    public static CurrencyType fromString(String currencyType) {
        CurrencyType type = roles.get(currencyType.toUpperCase());
        if (type == null) {
            throw new RuntimeException("Неверный тип валюты");
        }
        return type;
    }
}
