package ru.liga.predication;

import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class TomorrowPredication implements IPredication {

    public List<DateAndCourse> rate(List<DateAndCourse> course) {

        LocalDate date = LocalDate.now().plusDays(1);
        double newCourse = 0;
        while (!date.equals(course.get(0).getDate())) {
            newCourse = 0;
            for (int i = 0; i < 7; i++) {
                DateAndCourse dateAndCourse = course.get(i);
                newCourse += dateAndCourse.getCourse();
            }
            newCourse = newCourse / 7;
            course.add(0, new DateAndCourse(newCourse, course.get(0).getDate().plusDays(1)));
        }
        DateAndCourse dateAndCourse = new DateAndCourse(newCourse, date);
        return Collections.singletonList(dateAndCourse);

    }


}
