package ru.liga.bot.command.operations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.liga.bot.Utils;

import java.util.Collections;

public class CurrencyForecastCommand extends BotCommand {
    private Logger logger = LoggerFactory.getLogger(CurrencyForecastCommand.class);

    public CurrencyForecastCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }


    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

//        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName,
//                this.getCommandIdentifier()));
//        sendAnswer(absSender, chat.getId(), Collections.singletonList(OperationEnum.ADDITION), this.getDescription(),
//                this.getCommandIdentifier(), userName);
//        logger.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName,
//                this.getCommandIdentifier()));
    }
}