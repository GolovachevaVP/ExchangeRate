package ru.liga.telegram_bot.servise;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.telegram_bot.property.BotConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ru.liga.ExchangeRate.invoke;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            String answer = "";
            if (messageText.equals("/start")) {
                answer = startCommandReceived(chatId);
                sendMessage(chatId, answer);
            } else {
                try {
                    if (messageText.contains("graph")) {
                        invoke(messageText);
                        try {
                            sendPhoto(chatId);
                        } catch (FileNotFoundException | TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else {
                        for (String s : invoke(messageText)) {
                            answer += s + "\n";
                        }
                        sendMessage(chatId, answer);
                    }
                } catch (RuntimeException | IOException e) {
                    sendMessage(chatId, e.getMessage());

                }
            }

        }
    }

    private String startCommandReceived(Long chatId) {

        return "Здравствуй! Я телеграм-бот, который поможет тебе узнать курс валюты.\n\n" +
                "В моей программе есть несколько видов валют: USD, EUR, TRY, BGN, AND.\n" +
                "Четыре возможных периода прогноза: day tomorrow (на завтра), day dd.MM.yyyy (на определённую дату, " +
                "например, 12.10.2023), period week (на неделю), period month(на месяц).\n"+
                "Разные алгоритмы расчёта курса валют: mist (мистический), lastYear (прошлогодний), linReg (линейная регрессия).\n" +
                "Также, если выберешь период прогноза \"period week\" или \"period month\", не забудь указать " +
                "один из двух возможных форматов вывода: output list (сообщением в чате), output graph (графиком в чате)!\n" +
                "При выборе вывода \\\"output graph\\\", ты можешь запросить от 1 до 5 валют одновременно!\n\n" +
                "Вот, несколько примеров, которые можно взять за основу твоих запросов:\n" +
                "rate TRY -date tomorrow -alg mist\n" +
                "rate TRY -date 22.02.2030 -alg mist\n" +
                "rate USD -period week -alg mist -output list\n" +
                "rate USD,TRY -period month -alg lastYear -output graph\n" +
                "При выборе вывода \"output graph\", ты можешь запросить от 1 до 5 валют одновременно!\n\n" +
                "С объяснением моего запуска я закончил, а теперь попробуй узнать у меня прогноз курса валют," +
                " введя его в строку ввода сообщения!";
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

}

