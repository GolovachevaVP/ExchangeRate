package ru.liga.enums;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommandsTelegramBotEnum {

    START("/start"),
    HELP("/help"),
    OTHER_REQUEST("");
    final String command;

    CommandsTelegramBotEnum(String command) {
        this.command = command;
    }

    private static final Map<String, CommandsTelegramBotEnum> roles = Arrays
            .stream(CommandsTelegramBotEnum.values())
            .map(r -> new AbstractMap.SimpleEntry<>(r.command, r))
            .collect(
                    Collectors.toMap(
                            AbstractMap.SimpleEntry::getKey,
                            AbstractMap.SimpleEntry::getValue
                    )
            );

    public static CommandsTelegramBotEnum fromString(String command) {
        CommandsTelegramBotEnum commandsTelegramBotEnum = roles.get(command);
        if (commandsTelegramBotEnum == null) {
            return CommandsTelegramBotEnum.OTHER_REQUEST;
        }
        return commandsTelegramBotEnum;
    }
}
