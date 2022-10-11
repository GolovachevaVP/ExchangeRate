package ru.liga.telegram_bot.servise;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ru.liga.telegram_bot.property.BotConfig;

import java.io.IOException;
import java.util.logging.Level;

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
            if(messageText.contains("graph")){
                SendPhoto sendPhoto= new SendPhoto();
                InputFile photo = new InputFile("D:\\progi\\ExchangeRate_2.0\\line_chart.png");
                sendPhoto.setPhoto(photo);
                sendPhoto.setChatId(String.valueOf(chatId));
                //return sendPhoto;

            }
            

            switch (messageText) {
                case "/start":
                    answer = startCommandReceived(chatId, userName);
                    break;
                default:
                    try {
                        for(String s :invoke(chatId,messageText)){
                            answer=s;
                            sendMessage(chatId, answer);
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);

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

}

