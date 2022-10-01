package ru.liga;

import java.io.IOException;

public class WrongCurrencyException extends RuntimeException {
    public WrongCurrencyException() {
        super("Неправильный запрос");
    }
}
