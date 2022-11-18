package ru.liga.enums;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CurrencyTypeEnum {

    USD("USD"),
    EUR("EUR"),
    AMD("AMD"),
    BGN("BGN"),
    TRY("TRY");
    final String currencyType;

    CurrencyTypeEnum(String currencyType) {
        this.currencyType = currencyType;
    }

    private static final Map<String, CurrencyTypeEnum> roles = Arrays
            .stream(CurrencyTypeEnum.values())
            .map(r -> new AbstractMap.SimpleEntry<>(r.currencyType, r))
            .collect(
                    Collectors.toMap(
                            AbstractMap.SimpleEntry::getKey,
                            AbstractMap.SimpleEntry::getValue
                    )
            );

    public static CurrencyTypeEnum fromString(String currencyType) {
        CurrencyTypeEnum type = roles.get(currencyType.toUpperCase());
        if (type == null) {
            throw new RuntimeException("Неверный тип валюты");
        }
        return type;
    }
}
