package ru.liga.telegrambot.servise;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.enums.CommandsTelegramBotEnum;
import ru.liga.telegrambot.property.BotConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ru.liga.ExchangeRate.invoke;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private static final String START_ANSWER = "Здравствуй! Я телеграм-бот, который поможет тебе узнать курс валюты.\n\n" +
            "Вот, несколько примеров, которые можно взять за основу твоих запросов:\n" +
            "rate TRY -date tomorrow -alg mist\n" +
            "rate TRY -date 22.02.2030 -alg mist\n" +
            "rate USD -period week -alg mist -output list\n" +
            "rate USD,TRY -period month -alg lastYear -output graph\n\n" +
            "С объяснением моего запуска я закончил, а теперь попробуй узнать у меня прогноз курса валют," +
            " введя его в строку ввода сообщения! Если тебе нужна помощь, напиши /help";

    private static final String HELP_ANSWER = "Чтобы запустить меня, ты должен в строке ввода написать сообщение по одному из образцов:\n\n" +
            "\"Образец №1.\"\n\"rate 1 -day 2 -alg 3 \".\n\n" +
            "Вместо цифры 1 подставь одну из валют: USD, EUR, TRY, BGN, AMD\n\n" +
            "Вместо цифры 2 подставь один из периодов прогноза: tomorrow (на завтра), dd.MM.yyyy " +
            "(на определённую дату, например, 12.10.2023) \n\n" +
            "Вместо цифры 3 подставь один из алгоритмов расчёта курса валют: " +
            "mist (мистический), lastYear (прошлогодний), linReg (линейная регрессия)\n\n\n" +
            "\"Образец №2.\"\n\"rate 1 -period 2 -alg 3 -output 4\".\n\n" +
            "Вместо цифры 1 подставь одну из валют: USD, EUR, TRY, BGN, AMD\n\n" +
            "Вместо цифры 2 подставь один из периодов прогноза: week (на неделю), month (на месяц) \n\n" +
            "Вместо цифры 3 подставь один из алгоритмов расчёта курса валют: " +
            "mist (мистический), lastYear (прошлогодний), linReg (линейная регрессия)\n\n" +
            "Вместо цифры 4 подставь один из форматов вывода: output list (сообщением в чате), " +
            "output graph (графиком в чате)\n" +
            "При выборе вывода \"output graph\", ты можешь запросить от 1 до 5 валют одновременно!";

    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            if (messageText.equals("")) {
                sendMessage(chatId, startCommandReceived());
            }
            CommandsTelegramBotEnum commandsTelegramBotEnum = CommandsTelegramBotEnum.fromString(messageText);
            switch (commandsTelegramBotEnum) {
                case START:
                    log.info("пользователь ввел команду /start");
                    sendMessage(chatId, startCommandReceived());
                    break;
                case HELP:
                    log.info("пользователь ввел команду /help");
                    sendMessage(chatId, helpCommandReceived());
                    break;
                case OTHER_REQUEST:
                    log.info("пользователь ввел запрос - {}", messageText);
                    try {
                        if (messageText.contains("graph")) {
                            invoke(messageText);
                            try {
                                log.info("отправление графика курса валют на запрос пользователя");
                                sendPhoto(chatId);
                            } catch (FileNotFoundException | TelegramApiException e) {
                                e.printStackTrace();
                            }
                        } else {
                            log.info("отправление сообщения с прогнозом курса валюты");
                            sendMessage(chatId, invoke(messageText));
                        }
                    } catch (RuntimeException | IOException e) {
                        sendMessage(chatId, e.getMessage());
                    }
            }
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage(String.valueOf(chatId), textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка при попытке отправки сообщения");
        }
    }

    private void sendPhoto(long chatId) throws FileNotFoundException, TelegramApiException {
        File image = ResourceUtils.getFile("src/main/resources/line_chart.png");
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(image));
        sendPhoto.setChatId(String.valueOf(chatId));
        sendPhoto.setCaption("График валют");
        execute(sendPhoto);
    }

    private String startCommandReceived() {
        return START_ANSWER;
    }

    private String helpCommandReceived() {
        return HELP_ANSWER;
    }
}

