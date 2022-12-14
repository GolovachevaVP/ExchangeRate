package ru.liga.utils;

import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourseDto;
import ru.liga.enums.CurrencyTypeEnum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CSVReader {
    /**
     * Извлекает из файла csv столбцы с датой и курсом и записывает их в ArrayList<DateAndCourse> course
     *
     * @return ArrayList<DateAndCourse> course
     */
    private static final String COURSE_NAME = "curs";
    private static final String DATE_NAME = "data";

    public List<DateAndCourseDto> getCSVRows(CurrencyTypeEnum currencyTypeEnum) throws IOException {
        log.debug("читает файлы scv и добавляет в ArrayList дату и курс валюты");
        String line;
        List<DateAndCourseDto> course = new ArrayList<>();
        String csvFilePath = "src/main/resources/csv/" + currencyTypeEnum + ".csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            List<String> firstLine = List.of(br.readLine().split(";"));
            int coursePosition = firstLine.indexOf(COURSE_NAME);
            int datePosition = firstLine.indexOf(DATE_NAME);
            while ((line = br.readLine()) != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
                String[] cols = line.split(";");
                try {
                    double courseValue = Double.parseDouble(cols[coursePosition]);
                    LocalDate date = LocalDate.parse(cols[datePosition], formatter);
                    course.add(new DateAndCourseDto(courseValue, date));
                } catch (Exception ignored) {
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Для выбранной валюты нет данных");
        }
        log.debug("алгоритм отработан");
        return course;
    }
}
