package ru.liga;

import ru.liga.predication.TomorrowPredication;
import ru.liga.predication.WeekPredication;
import ru.liga.validation.CurrencyValidation;
import ru.liga.validation.PredicateValidator;

import java.io.IOException;
import java.util.Scanner;
import static ru.liga.utils.CSVReader.getCSVRows;

public class ExchangeRate {


    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        initConsole();
    }

    public static void invoke(Scanner scan) throws IOException {
        System.out.print("Прогнозирование курса валют. Введите запрос, по образцу:\"USD tomorrow или USD week.\" " +
                "\nВведите Ваш запрос - ");
        String request = scan.nextLine();
        CurrencyValidation validationCurrency = new CurrencyValidation();
        PredicateValidator validatorPredicate = new PredicateValidator();
        TomorrowPredication predicationTomorrow = new TomorrowPredication();
        WeekPredication weekTomorrow = new WeekPredication();
        if (validatorPredicate.validate(request).contains("tomorrow")) {
            predicationTomorrow.rate(getCSVRows(validationCurrency.validate(request)), validationCurrency.validate(request));
        }
        if (validatorPredicate.validate(request).contains("week")){
            weekTomorrow.rate(getCSVRows(validationCurrency.validate(request)), validationCurrency.validate(request));
        }
        initConsole();
    }

    public static void initConsole() throws IOException {
        Scanner scan = new Scanner(System.in);
        try {
            invoke(scan);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            initConsole();
        }
    }


}