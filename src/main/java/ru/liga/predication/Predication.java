package ru.liga.predication;

import ru.liga.dto.DateAndCourseDto;

import java.util.List;

public interface Predication {
    public List<DateAndCourseDto> rate(List<DateAndCourseDto> course, String algorithmType);
}
