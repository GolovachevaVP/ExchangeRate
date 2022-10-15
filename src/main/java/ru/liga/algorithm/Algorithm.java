package ru.liga.algorithm;


import ru.liga.dto.DateAndCourseDto;

import java.time.LocalDate;
import java.util.List;

public interface Algorithm {

    public Double algorithm(List<DateAndCourseDto> course, LocalDate date);



}
