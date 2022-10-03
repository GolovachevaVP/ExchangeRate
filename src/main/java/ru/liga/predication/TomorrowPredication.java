package ru.liga.predication;

import ru.liga.DateAndCourse;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static ru.liga.DateAndCourse.localDateToString;

public class TomorrowPredication implements IPredication{

    public List<String> rate(List<DateAndCourse> course, String currencyType)  {
        LocalDate date = LocalDate.now().plusDays(1);
        List<String> result = new ArrayList<>();
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        while (!date.equals(course.get(0).getDate())) {
            double newCourse = 0;
            for (int i = 0; i < 7; i++) {
                newCourse += course.get(i).getCourse();
            }
            newCourse = newCourse / 7;
            course.add(0, new DateAndCourse(newCourse, course.get(0).getDate().plusDays(1)));
        }
        result.add("rate " + currencyType + " tomorrow: " + localDateToString(course.get(0).getDate())
                + " - " + twoDForm.format(course.get(0).getCourse()));
        return result;
    }



}
