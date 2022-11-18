package ru.liga.predication;

import ru.liga.dto.DateAndCourseDto;
import ru.liga.enums.AlgorithmTypeEnum;

import java.util.List;

public interface Predication {
    public List<DateAndCourseDto> rate(List<DateAndCourseDto> course, AlgorithmTypeEnum algorithmTypeEnum);
}
