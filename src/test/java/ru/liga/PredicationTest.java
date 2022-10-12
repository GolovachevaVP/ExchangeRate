package ru.liga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.dto.DateAndCourse;
import ru.liga.predication.FutureDatePredication;
import ru.liga.predication.MonthPredication;
import ru.liga.predication.TomorrowPredication;
import ru.liga.predication.WeekPredication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class PredicationTest {


    @Test
    void correntCurrencyForecastForTomorrow(){
        TomorrowPredication tomorrowPredication = new TomorrowPredication();
        List<DateAndCourse> tomorrowCurrence = new ArrayList<>();
        List<DateAndCourse> result = new ArrayList<>();
        tomorrowCurrence.add(0,new DateAndCourse(1.0, LocalDate.now().plusDays(1)));
        result.add(tomorrowPredication.rate(currecyList, "alg linReg").get(0));
        Assertions.assertEquals(tomorrowCurrence.get(0).getCourse(), result.get(0).getCourse());
    }

    @Test
    void correntDateForecastForTomorrow(){
        TomorrowPredication tomorrowPredication = new TomorrowPredication();
        List<DateAndCourse> tomorrowCurrence = new ArrayList<>();
        List<DateAndCourse> result = new ArrayList<>();
        tomorrowCurrence.add(0,new DateAndCourse(1.0, LocalDate.now().plusDays(1)));
        result.add(tomorrowPredication.rate(currecyList, "alg linReg").get(0));
        Assertions.assertEquals(tomorrowCurrence.get(0).getDate(), result.get(0).getDate());
    }


    @Test
    void correntCurrencyForecastForTheDay(){
        FutureDatePredication futureDatePredication = new FutureDatePredication("15.12.2022");
        List<DateAndCourse> futureDayOfTheCurrence = new ArrayList<>();
        List<DateAndCourse> result = new ArrayList<>();
        futureDayOfTheCurrence.add(0,new DateAndCourse(1.0, LocalDate.of(2022, 12, 15)));
        result.add(futureDatePredication.rate(currecyList, "alg linReg").get(0));
        Assertions.assertEquals(futureDayOfTheCurrence.get(0).getCourse(), result.get(0).getCourse());
    }

    @Test
    void correntDateForecastForTheDay(){
        FutureDatePredication futureDatePredication = new FutureDatePredication("15.12.2022");
        List<DateAndCourse> futureDayOfTheCurrence = new ArrayList<>();
        List<DateAndCourse> result = new ArrayList<>();
        futureDayOfTheCurrence.add(0,new DateAndCourse(1.0, LocalDate.of(2022, 12, 15)));
        result.add(futureDatePredication.rate(currecyList, "alg linReg").get(0));
        Assertions.assertEquals(futureDayOfTheCurrence.get(0).getDate(), result.get(0).getDate());
    }


    @Test
    void correntCurrencyForecastForWeek(){
        WeekPredication weekPredication = new WeekPredication();
        List<DateAndCourse> weekCurrence = new ArrayList<>();
        List<DateAndCourse> result = new ArrayList<>();
        weekCurrence.add(0,new DateAndCourse(1.0, LocalDate.now().plusDays(7)));
        result.add(weekPredication.rate(currecyList, "alg linReg").get(6));
        Assertions.assertEquals(weekCurrence.get(0).getCourse(), result.get(0).getCourse());
    }

    @Test
    void correntDateForecastForWeek(){
        WeekPredication weekPredication = new WeekPredication();
        List<DateAndCourse> weekCurrence = new ArrayList<>();
        List<DateAndCourse> result = new ArrayList<>();
        weekCurrence.add(0,new DateAndCourse(1.0, LocalDate.now().plusDays(7)));
        result.add(weekPredication.rate(currecyList, "alg linReg").get(6));
        Assertions.assertEquals(weekCurrence.get(0).getDate(), result.get(0).getDate());
    }

    @Test
    void correntCurrencyForecastForMonth(){
        MonthPredication monthPredication = new MonthPredication();
        List<DateAndCourse> monthCurrence = new ArrayList<>();
        List<DateAndCourse> result = new ArrayList<>();
        monthCurrence.add(0,new DateAndCourse(1.0, LocalDate.now().plusDays(30)));
        result.add(monthPredication.rate(currecyList, "alg linReg").get(29));
        Assertions.assertEquals(monthCurrence.get(0).getCourse(), result.get(0).getCourse());
    }

    @Test
    void correntDateForecastForMonth(){
        MonthPredication monthPredication = new MonthPredication();
        List<DateAndCourse> monthCurrence = new ArrayList<>();
        List<DateAndCourse> result = new ArrayList<>();
        monthCurrence.add(0,new DateAndCourse(1.0, LocalDate.now().plusDays(30)));
        result.add(monthPredication.rate(currecyList, "alg linReg").get(29));
        Assertions.assertEquals(monthCurrence.get(0).getDate(), result.get(0).getDate());
    }



    List<DateAndCourse> currecyList = new ArrayList();
    {
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 10, 5)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 10, 4)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 10, 1)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 30)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 29)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 28)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 27)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 26)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 25)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 24)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 23)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 22)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 21)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 20)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 19)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 18)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 17)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 16)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 15)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 14)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 13)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 12)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 11)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 10)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 9)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 8)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 7)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 6)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 5)));
        currecyList.add(new DateAndCourse(1.0, LocalDate.of(2022, 9, 4)));



    }
}
