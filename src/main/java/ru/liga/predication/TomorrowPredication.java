package ru.liga.predication;

import ru.liga.DateAndCourse;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class TomorrowPredication implements IPredication{

    public void rate(List<DateAndCourse> course, String currencyType)  {
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


    private static String localDateToString(LocalDate date) {
        Locale localeRu = new Locale("ru", "RU");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy");
        String formattedString = date.format(formatter);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, localeRu);
        return dayOfWeek + " " + formattedString;
    }
}
