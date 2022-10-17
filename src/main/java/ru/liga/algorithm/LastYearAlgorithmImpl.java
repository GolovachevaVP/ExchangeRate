package ru.liga.algorithm;
import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourseDto;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class LastYearAlgorithmImpl implements Algorithm {
    public Double algorithm(List<DateAndCourseDto> course, LocalDate date) {
        log.debug("прогноз курса с помощью алгоритма - прошлогодний");
        log.debug("выбрана дата {}", date);
        LocalDate lastYearDate = date.minusYears(1);

        double curs = course.stream()
                .filter(dateAndCourseDto -> lastYearDate.equals(dateAndCourseDto.getDate()))
                .map(DateAndCourseDto::getCourse)
                .findFirst()
                .orElse(new DateAndCourseDto(0.0, null).getCourse());


        if (curs == 0) {
            if( course.size()>=2) {
                curs = course.get(1).getCourse();
            } else{
                throw new RuntimeException("Нет значение за вчерашний день");
            }
        }
        log.debug("алгоритм отработан");
        return curs;
    }
}

