package ru.liga;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) {
        log.debug("запуск телеграм-бота");
        SpringApplication.run(BotApplication.class, args);
        log.debug("алгоритм отработан");
    }
}