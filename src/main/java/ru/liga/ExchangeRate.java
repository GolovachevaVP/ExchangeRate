package ru.liga;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

public class ExchangeRate {


    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        for (; ;){
            dialogBox(args);
        }
    }

    /**
     * Диалоговое окно с пользователем.
     */
    public static void dialogBox(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Прогнозирование курса валют. \n1. Валюта - евро");
        System.out.println("2. Валюта - доллар США");
        System.out.println("3. Валюта - турецкая лира");
        System.out.print("Введите нужный Вам вариант: ");
        int request1 = scan.nextInt();
        int request2;
        try {
            switch (request1) {
                case 1:
                    System.out.println("1. Курс валюты на завтрашний день");
                    System.out.println("2. Курс валюты на 7 дней");
                    System.out.print("Введите нужный Вам вариант: ");
                    request2 = scan.nextInt();
                    switch (request2) {
                        case 1:
                            System.out.println("rate EUR tomorrow: " + rateTomorrow(
                                    readerCSV("src/main/resources/EUR.csv")));
                            break;
                        case 2:
                            System.out.println("rate EUR week: ");
                            rateWeek(readerCSV("src/main/resources/EUR.csv"));
                            break;
                        default:
                            System.out.println("Неверный запрос");
                    }
                    break;

                case 2:
                    System.out.println("1. Курс валюты на завтрашний день");
                    System.out.println("2. Курс валюты на 7 дней");
                    System.out.print("Выберите необходимый вариант: ");
                    request2 = scan.nextInt();
                    switch (request2) {
                        case 1:
                            System.out.println("rate USD tomorrow: " + rateTomorrow(
                                    readerCSV("src/main/resources/USD.csv")));
                            break;
                        case 2:
                            System.out.println("rate USD week: ");
                            rateWeek(readerCSV("src/main/resources/USD.csv"));
                            break;
                        default:
                            System.out.println("Неверный запрос");
                    }
                    break;

                case 3:
                    System.out.println("1. Курс валюты на завтрашний день");
                    System.out.println("2. Курс валюты на 7 дней");
                    System.out.print("Выберите необходимый вариант: ");
                    request2 = scan.nextInt();
                    switch (request2) {
                        case 1:
                            System.out.println("rate TRY tomorrow: " + rateTomorrow(
                                    readerCSV("src/main/resources/TRY.csv")));
                            break;
                        case 2:
                            System.out.println("rate TRY week: ");
                            rateWeek(readerCSV("src/main/resources/TRY.csv"));
                            break;
                        default:
                            System.out.println("Неверный запрос");
                    }
                    break;
                default:
                    System.out.println("Неверный запрос");
            }
        }catch (FileNotFoundException exc){
            System.out.println("Файл не найден");
        }
    }

    /**
     * Извлекает из файла csv столбцы с датой и курсом и записывает их в ArrayList<DateAndCourse> course
     * @return ArrayList<DateAndCourse> course
     */
    public static List readerCSV(String csvFile) throws IOException {
        String line;
        String[] cols;
        List<String> courseString = new ArrayList<>();
        List<String> dateString = new ArrayList<>();
        List<DateAndCourse> course = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                cols = line.split(";");
                courseString.add((cols[2]));
                dateString.add((cols[1]));
            }
            courseString.remove(0);
            dateString.remove(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

            for (int i = 0, j = 0; i < courseString.size(); i++, j++) {
                course.add(new DateAndCourse(Double.valueOf(courseString.get(i)), LocalDate.parse(dateString.get(j), formatter)));
            }
        }catch (IndexOutOfBoundsException exc){
            System.out.println("Файл не найден");
        }
        return course;
    }

    /**
     * Прогнозирует курс на завтрашний день.
     * @return возвращает день в формате d.MM.yyyy и курс в формате #.##
     */
    public static String rateTomorrow(List<DateAndCourse> course) {
        LocalDate date = LocalDate.now();
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        while (!date.equals(course.get(0).date)) {
            double newCourse = 0;
            for (int i = 0; i < 7; i++) {
                newCourse += course.get(i).course;
            }
            newCourse = newCourse / 7;
            course.add(0, new DateAndCourse(newCourse, course.get(0).date.plusDays(1)));
        }
        int year = course.get(0).date.getYear();
        int month = course.get(0).date.getMonthValue();
        int dayOfMonth = course.get(0).date.getDayOfMonth();
        DayOfWeek dayOfWeek = course.get(0).date.getDayOfWeek();
        Locale localeRu = new Locale("ru", "RU");

        return dayOfWeek.getDisplayName(TextStyle.SHORT, localeRu) +
                " " + dayOfMonth + "." + month + "." + year + " - " + twoDForm.format(course.get(0).course);
    }

    /**
     * Прогнозирует курс на неделю.
     * Выводит дни в формате d.MM.yyyy и курс в формате #.##
     */
    public static void rateWeek(List<DateAndCourse> course) {
        LocalDate date = LocalDate.now().plusDays(6);
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        while (!date.equals(course.get(0).date)) {
            double newCourse = 0;
            for (int i = 0; i < 7; i++) {
                newCourse += course.get(i).course;
            }
            newCourse = newCourse / 7;
            course.add(0, new DateAndCourse(newCourse, course.get(0).date.plusDays(1)));
        }

        Locale localeRu = new Locale("ru", "RU");

        for(int i=6; i>=0; i--){
            int year = course.get(i).date.getYear();
            int month = course.get(i).date.getMonthValue();
            int dayOfMonth = course.get(i).date.getDayOfMonth();
            DayOfWeek dayOfWeek = course.get(i).date.getDayOfWeek();
            System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, localeRu) +
                    " " + dayOfMonth + "." + month + "." + year + " - " + twoDForm.format(course.get(i).course));
        }
    }
}