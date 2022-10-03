package ru.liga.utils;

import ru.liga.DateAndCourse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    /**
     * Извлекает из файла csv столбцы с датой и курсом и записывает их в ArrayList<DateAndCourse> course
     *
     * @return ArrayList<DateAndCourse> course
     */
    public static List<DateAndCourse> getCSVRows(String currencyType) throws IOException {
        String line;
        final int COURSE_POSITION = 2;
        final int DATE_POSITION = 1;
        List<DateAndCourse> course = new ArrayList<>();
        String csvFilePath = "src/main/resources/" + currencyType + ".csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            while ((line = br.readLine()) != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                String[] cols;
                cols = line.split(";");
                try {
                    double courseValue = Double.parseDouble(cols[COURSE_POSITION]);
                    LocalDate date = LocalDate.parse(cols[DATE_POSITION], formatter);
                    course.add(new DateAndCourse(courseValue, date));
                } catch (Exception e) {
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Для выбранной валюты нет данных");
        }
        return course;
    }
}
