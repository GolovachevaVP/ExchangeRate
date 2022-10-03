
package ru.liga;

        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.text.DecimalFormat;
        import java.time.LocalDate;
        import java.time.format.DateTimeFormatter;
        import java.time.format.TextStyle;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Locale;
        import java.util.Scanner;

public class ExchangeRate {


    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {

        initConsole();
    }

    public static void invoke(Scanner scan) throws IOException {
        System.out.print("Прогнозирование курса валют. Введите запрос, по образцу:\"USD tomorrow или USD week.\" " +
                "\nВведите Ваш запрос - ");
        String request = scan.nextLine();
        String currencyType;
        if (request.contains("tomorrow")) {
            currencyType = request.replaceAll("tomorrow", "");
            currencyType = currencyType.replaceAll(" ", "");
            rateTomorrow(readerCSV(currencyType), currencyType);
        } else if (request.contains("week")) {
            currencyType = request.replaceAll("week", "");
            currencyType = currencyType.replaceAll(" ", "");
            rateWeek(readerCSV(currencyType), currencyType);
        } else {
            throw new RuntimeException("Неправильный запрос");
        }
        initConsole();
    }

    public static void initConsole() {
        Scanner scan = new Scanner(System.in);
        try {
            invoke(scan);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            initConsole();
        }
    }


    /**
     * Извлекает из файла csv столбцы с датой и курсом и записывает их в ArrayList<DateAndCourse> course
     *
     * @return ArrayList<DateAndCourse> course
     */
    public static List<DateAndCourse> readerCSV(String currencyType) throws IOException {
        String line;
        String[] cols;
        List<String> courseString = new ArrayList<>();
        List<String> dateString = new ArrayList<>();
        List<DateAndCourse> course = new ArrayList<>();
        String csvFilePath = "src/main/resources/" + currencyType + ".csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
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
        return course;
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
        System.out.println("rate " + currencyType + " tomorrow:" + localDateToString(course.get(0).date) + twoDForm.format(course.get(0).course));
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
        System.out.println("rate " + currencyType + " week:");
        for (int i = 6; i >= 0; i--) {
            System.out.println(localDateToString(course.get(i).date) + twoDForm.format(course.get(i).course));
        }
    }

    private static String localDateToString(LocalDate date) {
        Locale localeRu = new Locale("ru", "RU");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LL yyyy");
        String formattedString = date.format(formatter);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, localeRu);
        return dayOfWeek + " " + formattedString;
    }
}