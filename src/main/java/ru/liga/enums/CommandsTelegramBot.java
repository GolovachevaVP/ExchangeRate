package ru.liga.enums;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommandsTelegramBot {

    START("/start"),
    HELP("/help"),
    OTHER_REQUEST("");
    final String command;

    CommandsTelegramBot(String command) {
        this.command = command;
    }

    private static final Map<String, CommandsTelegramBot> roles = Arrays
            .stream(CommandsTelegramBot.values())
            .map(r -> new AbstractMap.SimpleEntry<>(r.command, r))
            .collect(
                    Collectors.toMap(
                            AbstractMap.SimpleEntry::getKey,
                            AbstractMap.SimpleEntry::getValue
                    )
            );

    public static CommandsTelegramBot fromString(String command) {
        CommandsTelegramBot commandsTelegramBot = roles.get(command);
        if (commandsTelegramBot == null) {
            return CommandsTelegramBot.OTHER_REQUEST;
        }
        return commandsTelegramBot;
    }
}
