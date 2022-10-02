package ru.liga;

public class WrongCurrencyException extends RuntimeException {
    public WrongCurrencyException(String exception) {
        super(exception);
    }
}
