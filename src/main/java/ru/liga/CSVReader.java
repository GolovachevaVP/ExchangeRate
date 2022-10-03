package ru.liga;

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
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            while ((line = br.readLine()) != null) {
                cols = line.split(";");
                courseString.add((cols[2]));
                dateString.add((cols[1]));
            }
            courseString.remove(0);
            dateString.remove(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

            for (int i = 0, j = 0; i < courseString.size(); i++, j++) {
                course.add(new DateAndCourse(Double.valueOf(courseString.get(i)), LocalDate.parse(dateString.get(j), formatter)));
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException("Неверный тип валюты");
        }
        return course;
    }
}
