package ru.liga;

import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourseDto;
import ru.liga.dto.ForecastOutput;
import ru.liga.graph.LineChartForCurrencyExchangeRateForecastingGraphImpl;
import ru.liga.predication.Predication;
import ru.liga.predication.PredicationFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.liga.utils.ChekingTheEnteredRequest.*;
import static ru.liga.utils.CSVReader.getCSVRows;

@Slf4j

public class ExchangeRate {
    private static final int POSITION_FOR_CURRENCY = 0;

    public static List<String> invoke(String text) throws IOException {

        log.debug("чтение запроса пользователя");

        String request = text;

        List<String> result = new ArrayList<>();
        String predicatorType = getPredicatorType(request);
        String outputType = getOutputType(request);
        if (!outputType.equals("") && (!predicatorType.equals("month") && !predicatorType.equals("week"))) {
            throw new RuntimeException("Уберите формат вывода.");
        }
        String algorithmType = getAlgorithmType(request);
        PredicationFactory predFactory = new PredicationFactory();
        Predication predicator = predFactory.getPredication(predicatorType);
        List<String> numberOfCurr = searchCurrencies(request);

        if (outputType.equals("") || outputType.equals("output list")) {
            String currencyType = numberOfCurr.get(POSITION_FOR_CURRENCY);
            List<DateAndCourseDto> csvRows = getCSVRows(currencyType);
            System.out.println(request + ":");
            for (DateAndCourseDto res : predicator.rate(csvRows, algorithmType)) {
                result.add(res.toString());
            }
        } else {
            System.setProperty("java.awt.headless", "false");
            LineChartForCurrencyExchangeRateForecastingGraphImpl graph = new LineChartForCurrencyExchangeRateForecastingGraphImpl();
            graph.initUI(numberOfCurr, predicator, algorithmType);
        }
        log.debug("алгоритм отработан");
        return result;

    }


}