package ru.liga.graph;

import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import ru.liga.dto.DateAndCourseDto;
import ru.liga.enums.AlgorithmType;
import ru.liga.enums.CurrencyType;
import ru.liga.predication.Predication;
import ru.liga.utils.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
public class LineChartForCurrencyExchangeRateForecastingGraphImpl extends JFrame {
    public void initUI(List<CurrencyType> numberOfCurr, Predication predicator, AlgorithmType algorithmType) throws IOException {
        log.debug("обрабатывает информацию из других методов для постройки графика");

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        for (CurrencyType currencyType : numberOfCurr) {
            CurrencyType curr = currencyType;
            CSVReader csvReader = new CSVReader();
            List<DateAndCourseDto> csvRows = csvReader.getCSVRows(curr);
            List<DateAndCourseDto> result = predicator.rate(csvRows, algorithmType);
            dataset.addSeries(createDataset(result, curr));
        }

        JFreeChart chart = createChart(dataset, numberOfCurr);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);

        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        log.debug("алгоритм отработан");
    }

    private static TimeSeries createDataset(List<DateAndCourseDto> result, CurrencyType currency) {
        log.debug("обрабатывает данные для постройки графика");

        TimeSeries series = new TimeSeries("rate " + currency);
        for (DateAndCourseDto dateAndCourseDto : result) {
            int day = dateAndCourseDto.getDate().getDayOfMonth();
            int month = dateAndCourseDto.getDate().getMonthValue();
            int year = dateAndCourseDto.getDate().getYear();
            series.add(new Day(day, month, year), dateAndCourseDto.getCourse());
        }
        log.debug("алгоритм отработан");
        return series;
    }

    private static JFreeChart createChart(final XYDataset dataset, List<CurrencyType> numberOfCurr) throws IOException {
        log.debug("строит график и сохраняет его в png-формате.");
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Currency forecast",
                "Period",
                "Currency",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        for (int i = 0; i < numberOfCurr.size(); i++) {
            Color color = new Color((int) (Math.random() * 0x1000000));
            renderer.setSeriesPaint(i, color);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Currency forecast",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        DateAxis dateAxis = new DateAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("dd.MM"));
        plot.setDomainAxis(dateAxis);
        ChartUtils.saveChartAsPNG(new File("src/main/resources/line_chart.png"), chart, 700, 600);
        log.debug("алгоритм отработан");
        return chart;
    }
}