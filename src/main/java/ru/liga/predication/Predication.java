package ru.liga.predication;

import ru.liga.dto.DateAndCourseDto;
import ru.liga.enums.AlgorithmType;

import java.util.List;

public interface Predication {
    public List<DateAndCourseDto> rate(List<DateAndCourseDto> course, AlgorithmType algorithmType);
}
