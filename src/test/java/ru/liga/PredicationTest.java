package ru.liga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.dto.DateAndCourseDto;
import ru.liga.predication.FutureDatePredicationPredicationImpl;
import ru.liga.predication.MonthPredicationPredicationImpl;
import ru.liga.predication.TomorrowPredicationPredicationImpl;
import ru.liga.predication.WeekPredicationPredicationImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.liga.enums.AlgorithmTypeEnum.*;


public class PredicationTest {


    @Test
    void correntCurrencyForecastForTomorrow() {
        TomorrowPredicationPredicationImpl tomorrowPredicationPredicationImpl = new TomorrowPredicationPredicationImpl();
        List<DateAndCourseDto> tomorrowCurrence = new ArrayList<>();
        List<DateAndCourseDto> result = new ArrayList<>();
        tomorrowCurrence.add(0, new DateAndCourseDto(1.0, LocalDate.now().plusDays(1)));
        result.add(tomorrowPredicationPredicationImpl.rate(currecyList, LINEAR).get(0));
        Assertions.assertEquals(tomorrowCurrence.get(0).getCourse(), result.get(0).getCourse());
    }

    @Test
    void correntDateForecastForTomorrow() {
        TomorrowPredicationPredicationImpl tomorrowPredicationPredicationImpl = new TomorrowPredicationPredicationImpl();
        List<DateAndCourseDto> tomorrowCurrence = new ArrayList<>();
        List<DateAndCourseDto> result = new ArrayList<>();
        tomorrowCurrence.add(0, new DateAndCourseDto(1.0, LocalDate.now().plusDays(1)));
        result.add(tomorrowPredicationPredicationImpl.rate(currecyList, LINEAR).get(0));
        Assertions.assertEquals(tomorrowCurrence.get(0).getDate(), result.get(0).getDate());
    }


    @Test
    void correntCurrencyForecastForTheDay() {
        FutureDatePredicationPredicationImpl futureDatePredicationPredicationImpl = new FutureDatePredicationPredicationImpl("15.12.2022");
        List<DateAndCourseDto> futureDayOfTheCurrence = new ArrayList<>();
        List<DateAndCourseDto> result = new ArrayList<>();
        futureDayOfTheCurrence.add(0, new DateAndCourseDto(1.0, LocalDate.of(2022, 12, 15)));
        result.add(futureDatePredicationPredicationImpl.rate(currecyList, LINEAR).get(0));
        Assertions.assertEquals(futureDayOfTheCurrence.get(0).getCourse(), result.get(0).getCourse());
    }

    @Test
    void correntDateForecastForTheDay() {
        FutureDatePredicationPredicationImpl futureDatePredicationPredicationImpl = new FutureDatePredicationPredicationImpl("15.12.2022");
        List<DateAndCourseDto> futureDayOfTheCurrence = new ArrayList<>();
        List<DateAndCourseDto> result = new ArrayList<>();
        futureDayOfTheCurrence.add(0, new DateAndCourseDto(1.0, LocalDate.of(2022, 12, 15)));
        result.add(futureDatePredicationPredicationImpl.rate(currecyList, LINEAR).get(0));
        Assertions.assertEquals(futureDayOfTheCurrence.get(0).getDate(), result.get(0).getDate());
    }


    @Test
    void correntCurrencyForecastForWeek() {
        WeekPredicationPredicationImpl weekPredicationPredicationImpl = new WeekPredicationPredicationImpl();
        List<DateAndCourseDto> weekCurrence = new ArrayList<>();
        List<DateAndCourseDto> result = new ArrayList<>();
        weekCurrence.add(0, new DateAndCourseDto(1.0, LocalDate.now().plusDays(7)));
        result.add(weekPredicationPredicationImpl.rate(currecyList, LINEAR).get(6));
        Assertions.assertEquals(weekCurrence.get(0).getCourse(), result.get(0).getCourse());
    }

    @Test
    void correntDateForecastForWeek() {
        WeekPredicationPredicationImpl weekPredicationPredicationImpl = new WeekPredicationPredicationImpl();
        List<DateAndCourseDto> weekCurrence = new ArrayList<>();
        List<DateAndCourseDto> result = new ArrayList<>();
        weekCurrence.add(0, new DateAndCourseDto(1.0, LocalDate.now().plusDays(7)));
        result.add(weekPredicationPredicationImpl.rate(currecyList, LINEAR).get(6));
        Assertions.assertEquals(weekCurrence.get(0).getDate(), result.get(0).getDate());
    }

    @Test
    void correntCurrencyForecastForMonth() {
        MonthPredicationPredicationImpl monthPredicationPredicationImpl = new MonthPredicationPredicationImpl();
        List<DateAndCourseDto> monthCurrence = new ArrayList<>();
        List<DateAndCourseDto> result = new ArrayList<>();
        monthCurrence.add(0, new DateAndCourseDto(1.0, LocalDate.now().plusDays(30)));
        result.add(monthPredicationPredicationImpl.rate(currecyList, LINEAR).get(29));
        Assertions.assertEquals(monthCurrence.get(0).getCourse(), result.get(0).getCourse());
    }

    @Test
    void correntDateForecastForMonth() {
        MonthPredicationPredicationImpl monthPredicationPredicationImpl = new MonthPredicationPredicationImpl();
        List<DateAndCourseDto> monthCurrence = new ArrayList<>();
        List<DateAndCourseDto> result = new ArrayList<>();
        monthCurrence.add(0, new DateAndCourseDto(1.0, LocalDate.now().plusDays(30)));
        result.add(monthPredicationPredicationImpl.rate(currecyList, LINEAR).get(29));
        Assertions.assertEquals(monthCurrence.get(0).getDate(), result.get(0).getDate());
    }

    List<DateAndCourseDto> currecyList = new ArrayList();
    {
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 10, 5)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 10, 4)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 10, 1)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 30)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 29)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 28)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 27)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 26)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 25)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 24)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 23)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 22)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 21)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 20)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 19)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 18)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 17)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 16)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 15)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 14)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 13)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 12)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 11)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 10)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 9)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 8)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 7)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 6)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 5)));
        currecyList.add(new DateAndCourseDto(1.0, LocalDate.of(2022, 9, 4)));
    }
}
