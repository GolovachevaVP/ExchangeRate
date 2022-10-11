package ru.liga;

import ru.liga.dto.DateAndCourse;
import ru.liga.graph.LineChartForCurrencyExchangeRateForecasting;
import ru.liga.predication.IPredication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.liga.dto.UserDto.*;
import static ru.liga.utils.CSVReader.getCSVRows;

public class ExchangeRate {


    public static void main(String[] args) throws IOException {
        invoke(12,"rate USD,TRY -period month -alg lastYear -output graph");
    }

    public static List<String>  invoke(long id,String text) throws IOException {
        String request = text;
        List<String> result = new ArrayList<>();
        String outputType = getOutputType(request);
        String predicatorType = getPredicatorType(request);
        String algorithmType = getAlgorithmType(request);
        IPredication predicator = IPredication.select(predicatorType);
        if (outputType.equals("") || outputType.equals("output list")) {
            String currencyType = getCurrencyType(request);
            List<DateAndCourse> csvRows = getCSVRows(currencyType);
            System.out.println(request + ":");
            for (DateAndCourse res : predicator.rate(csvRows, algorithmType)) {
                result.add(res.toString(currencyType));
            }
        } else {
            List<String> numberOfCurr = numberOfCurrencies(request);
            LineChartForCurrencyExchangeRateForecasting graph = new LineChartForCurrencyExchangeRateForecasting();
            graph.initUI(numberOfCurr, predicator, algorithmType);




        }
        return result;

    }

//    public static void initConsole() throws IOException {
//        Scanner scan = new Scanner(System.in);
//        try {
//            invoke(scan);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            initConsole();
//        }
//    }

}