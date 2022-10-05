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

public class WeekPredication implements IPredication {
    public List<DateAndCourse> rate(List<DateAndCourse> course) {
        LocalDate date = LocalDate.now().plusDays(7);
        List<DateAndCourse> result = new ArrayList<>();
        while (!date.equals(course.get(0).getDate())) {
            double newCourse = 0;
            for (int i = 0; i < 7; i++) {
                newCourse += course.get(i).getCourse();
            }
            newCourse = newCourse / 7;
            DateAndCourse dateAndCourse = new DateAndCourse(newCourse, course.get(0).
                    getDate().plusDays(1));
            course.add(0, dateAndCourse);
            result.add(dateAndCourse);
        }
        Collections.reverse(result);
        return result;
    }

}
