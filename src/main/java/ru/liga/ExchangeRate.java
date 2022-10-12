package ru.liga;

import ru.liga.dto.DateAndCourse;
import ru.liga.graph.LineChartForCurrencyExchangeRateForecasting;
import ru.liga.predication.IPredication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.liga.dto.UserDto.*;
import static ru.liga.utils.CSVReader.getCSVRows;

public class ExchangeRate {
    private static final int POSITION_FOR_CURRENCY = 0;


    public static void main(String[] args) throws IOException {
        invoke("rate USD -period week -alg linReg -output list");
    }

    public static List<String>  invoke(String text) throws IOException {
        String request = text;
        List<String> result = new ArrayList<>();
        String predicatorType = getPredicatorType(request);
        String outputType = getOutputType(request);
        if (!outputType.equals("") && (!predicatorType.equals("month") && !predicatorType.equals("week"))){
            throw new RuntimeException("Уберите формат вывода.");
        }
        String algorithmType = getAlgorithmType(request);
        IPredication predicator = IPredication.select(predicatorType);
        List<String> numberOfCurr = searchCurrencies(request);
        if (outputType.equals("") || outputType.equals("output list")) {
            String currencyType = numberOfCurr.get(POSITION_FOR_CURRENCY);
            List<DateAndCourse> csvRows = getCSVRows(currencyType);
            System.out.println(request + ":");
            for (DateAndCourse res : predicator.rate(csvRows, algorithmType)) {
                result.add(res.toString(currencyType));
            }
        } else {
            System.setProperty("java.awt.headless", "false");
            LineChartForCurrencyExchangeRateForecasting graph = new LineChartForCurrencyExchangeRateForecasting();
            graph.initUI(numberOfCurr, predicator, algorithmType);
        }
        return result;

    }



}