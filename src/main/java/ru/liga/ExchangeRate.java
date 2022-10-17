package ru.liga;

import lombok.extern.slf4j.Slf4j;
import ru.liga.dto.DateAndCourseDto;
import ru.liga.enums.AlgorithmType;
import ru.liga.enums.CurrencyType;
import ru.liga.enums.OutputType;
import ru.liga.graph.LineChartForCurrencyExchangeRateForecastingGraphImpl;
import ru.liga.predication.Predication;
import ru.liga.predication.PredicationFactory;
import ru.liga.utils.CSVReader;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static ru.liga.utils.ChekingTheEnteredRequest.*;
import static ru.liga.utils.ForecastOutput.dateAndCourseDtoToString;


@Slf4j
public class ExchangeRate {
    private static final int POSITION_FOR_CURRENCY = 0;

    public static String invoke(String text) throws IOException {

        log.debug("чтение запроса пользователя");

        List<String> courseList = new ArrayList<>();
        String predicatorType = getPredicatorType(text);
        OutputType outputType = getOutputType(text);
        if (!outputType.equals(OutputType.WITHOUT_OUTPUT_TYPE) && (!predicatorType.equals("month") && !predicatorType.equals("week"))) {
            throw new RuntimeException("Уберите формат вывода.");
        }
        AlgorithmType algorithmType = getAlgorithmType(text);
        PredicationFactory predFactory = new PredicationFactory();
        Predication predicator = predFactory.getPredication(predicatorType);
        List<CurrencyType> numberOfCurr = searchCurrencies(text);

        if (outputType.equals(OutputType.WITHOUT_OUTPUT_TYPE) || outputType.equals(OutputType.LIST)) {
            CurrencyType currencyType = numberOfCurr.get(POSITION_FOR_CURRENCY);
            CSVReader csvReader = new CSVReader();
            List<DateAndCourseDto> csvRows = csvReader.getCSVRows(currencyType);
            System.out.println(text + ":");
            for (DateAndCourseDto res : predicator.rate(csvRows, algorithmType)) {
                courseList.add(dateAndCourseDtoToString(res));
            }
        } else {
            System.setProperty("java.awt.headless", "false");
            LineChartForCurrencyExchangeRateForecastingGraphImpl graph = new LineChartForCurrencyExchangeRateForecastingGraphImpl();
            graph.initUI(numberOfCurr, predicator, algorithmType);
        }
        String result = "";
        for (String s : courseList) {
            result += s + "\n";
        }
        log.debug("алгоритм отработан");
        return result;
    }
}