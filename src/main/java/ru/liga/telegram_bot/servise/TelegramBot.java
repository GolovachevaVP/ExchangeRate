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
            String userName = update.getMessage().getChat().getFirstName();
            String answer = "";
            if (messageText.equals("/start")) {
                answer = startCommandReceived(chatId, userName);

            } else {
                try {
                    if (messageText.contains("graph")) {

                        invoke(chatId, messageText);

                        try {
                            sendPhoto(chatId);
                        } catch (FileNotFoundException | TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else
                        for (String s : invoke(chatId, messageText)) {
                            answer += s + "\n";
                        }
                    sendMessage(chatId, answer);
                } catch (RuntimeException | IOException e) {
                    sendMessage(chatId, e.getMessage());

                }
            }
            //sendMessage(chatId, answer);
        }
    }

    private String startCommandReceived(Long chatId, String userName) {

        return "Hi " + userName + ", nice to meet you!";
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

