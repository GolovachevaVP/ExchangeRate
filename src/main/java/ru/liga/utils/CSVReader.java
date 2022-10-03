package ru.liga.utils;

import ru.liga.DateAndCourse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
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
        String[] cols;
        List<String> courseString = new ArrayList<>();
        List<String> dateString = new ArrayList<>();
        List<DateAndCourse> course = new ArrayList<>();
        String csvFilePath = "src/main/resources/" + currencyType + ".csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            try {
                while ((line = br.readLine()) != null) {
                    cols = line.split(";");
                    course.add(new DateAndCourse(Double.parseDouble(cols[2]), LocalDate.parse(cols[1], formatter)));
                    courseString.add((cols[2]));
                    dateString.add((cols[1]));
                }
            }catch (NumberFormatException e){}
//            courseString.remove(0);
//            dateString.remove(0);
//
//
//            for (int i = 0, j = 0; i < courseString.size(); i++, j++) {
//                course.add(new DateAndCourse(Double.valueOf(courseString.get(i)), LocalDate.parse(dateString.get(j), formatter)));
//            }
        }catch (FileNotFoundException e){
            throw new RuntimeException("Для выбранной валюты нет данных");
        }
        return course;
    }
}
