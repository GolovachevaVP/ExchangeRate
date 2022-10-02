package ru.liga;

import java.util.ArrayList;
import java.util.Scanner;

import static ru.liga.CSVReader.getCSVRows;

public class ValidationDto {

    public static String getDataForCurrency(String input) {
        String[] currencyType = input.split(" ");
        return currencyType[0];
    }

    public static String getCurrencyForData(String input) {
        String[] dataType = input.split(" ");
        if(dataType.length!=1){
            return dataType[1];
        }
       else throw new WrongCurrencyException("Неверный запрос");
    }
}
