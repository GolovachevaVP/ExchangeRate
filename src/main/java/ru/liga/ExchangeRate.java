package ru.liga;

import ru.liga.dto.DateAndCourse;
import ru.liga.predication.IPredication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static ru.liga.utils.CSVReader.getCSVRows;
import static ru.liga.dto.UserDto.getCurrencyType;
import static ru.liga.dto.UserDto.getPredicatorType;

public class ExchangeRate {


    public static void main(String[] args) throws IOException {
        initConsole();
    }

    public static void invoke(Scanner scan) throws IOException {
        System.out.print("Прогнозирование курса валют. Введите запрос, по образцу:\"USD tomorrow или USD week.\" " +
                "\nВведите Ваш запрос - ");
        String request = scan.nextLine();
        String currencyType = getCurrencyType(request);
        List<DateAndCourse> csvRows = getCSVRows(currencyType);
        String predicatorType = getPredicatorType(request);
        IPredication predicator = IPredication.select(predicatorType);
        for (DateAndCourse result : predicator.rate(csvRows)) {
            System.out.println(result.toString(currencyType));
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