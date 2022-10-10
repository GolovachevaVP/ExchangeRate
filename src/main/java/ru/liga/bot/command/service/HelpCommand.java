package ru.liga.bot.command.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.liga.bot.Utils;


/**
 * Команда "Помощь"
 */
public class HelpCommand extends ServiceCommand {
    private Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName,
                this.getCommandIdentifier()));
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Я бот, который поможет Вам с прогнозом курса валют\n\n" +
                        "Валюты: AMD,BGN,EUR,TRY,USD\n"+
                        "Периоды прогноза: tomorrow, нужная вам дата в формате 01.01.2020, week, month\n"+
                        "Валюты: AMD,BGN,EUR,TRY,USD\n"+

                        "❗*Примеры команд*\n" +
                        "/plus - сложение\n" +
                        "/minus - вычитание\n" +
                        "/plusminus - сложение и вычитание\n" +
                        "/multiply - умножение\n" +
                        "/divide - деление\n" +
                        "/multdivide - умножение и деление\n" +
                        "/all - все четыре арифметических действия\n" +

                        "/help - помощь\n\n"
                       );
        logger.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName,
                this.getCommandIdentifier()));
    }
}