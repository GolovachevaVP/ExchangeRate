package ru.liga.predication;

import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekPredication implements IPredication {
    public List<DateAndCourse> rate(List<DateAndCourse> course) {
        LocalDate date = LocalDate.now().plusDays(7);
        List<DateAndCourse> result = new ArrayList<>();
        DateAndCourse dateAndCourse = null;
        while (!date.equals(course.get(0).getDate())) {
            double newCourse = 0;
            for (int i = 0; i < 7; i++) {
                newCourse += course.get(i).getCourse();
            }
            newCourse = newCourse / 7;

            dateAndCourse = new DateAndCourse(newCourse, course.get(0).
                    getDate().plusDays(1));
            course.add(0, dateAndCourse);

        }
        for(int i=6;i>=0; i--){
            result.add(course.get(i).getDate());
        }
        Collections.reverse(result);
        return result;
    }

}
