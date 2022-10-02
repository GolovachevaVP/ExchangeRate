package ru.liga;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static ru.liga.CSVReader.getCSVRows;

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
        if (validatorPredicate.validate(request).contains("tomorrow")) {
            rateTomorrow(getCSVRows(validationCurrency.validate(request)), validationCurrency.validate(request));
        }
        if (validatorPredicate.validate(request).contains("week")){
            rateWeek(getCSVRows(validationCurrency.validate(request)), validationCurrency.validate(request));
        }
        initConsole();
    }

    public static void initConsole() throws IOException {
        Scanner scan = new Scanner(System.in);
        try {
            invoke(scan);
        } catch (WrongCurrencyException e) {
            System.out.println(e.getMessage());
            initConsole();
        }
    }

    /**
     * Прогнозирует курс на завтрашний день.
     *
     * @return возвращает день в формате d.MM.yyyy и курс в формате #.##
     */
    public static void rateTomorrow(List<DateAndCourse> course, String currencyType) {
        LocalDate date = LocalDate.now().plusDays(1);
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        while (!date.equals(course.get(0).date)) {
            double newCourse = 0;
            for (int i = 0; i < 7; i++) {
                newCourse += course.get(i).course;
            }
            newCourse = newCourse / 7;
            course.add(0, new DateAndCourse(newCourse, course.get(0).date.plusDays(1)));
        }
        System.out.println("rate " + currencyType + " tomorrow: " + localDateToString(course.get(0).date)
                + " - " + twoDForm.format(course.get(0).course));
    }

    /**
     * Прогнозирует курс на неделю.
     * Выводит дни в формате d.MM.yyyy и курс в формате #.##
     */
    public static void rateWeek(List<DateAndCourse> course, String currencyType) {
        LocalDate date = LocalDate.now().plusDays(7);
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        while (!date.equals(course.get(0).date)) {
            double newCourse = 0;
            for (int i = 0; i < 7; i++) {
                newCourse += course.get(i).course;
            }
            newCourse = newCourse / 7;
            course.add(0, new DateAndCourse(newCourse, course.get(0).date.plusDays(1)));
        }
        System.out.println("rate " + currencyType + " week: ");
        for (int i = 6; i >= 0; i--) {
            System.out.println(localDateToString(course.get(i).date) + " - " + twoDForm.format(course.get(i).course));
        }
    }

    private static String localDateToString(LocalDate date) {
        Locale localeRu = new Locale("ru", "RU");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy");
        String formattedString = date.format(formatter);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, localeRu);
        return dayOfWeek + " " + formattedString;
    }
}