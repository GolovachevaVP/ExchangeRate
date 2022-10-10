package ru.liga.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
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
import ru.liga.dto.DateAndCourse;
import ru.liga.predication.IPredication;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import static ru.liga.utils.CSVReader.getCSVRows;

public class LineChartForCurrencyExchangeRateForecasting extends JFrame {
    public void initUI(List<String> numberOfCurr, IPredication predicator, String algorithmType) throws IOException {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        for (String currencyType : numberOfCurr) {
            String curr = currencyType;
            List<DateAndCourse> csvRows = getCSVRows(curr);
            List<DateAndCourse> result = predicator.rate(csvRows, algorithmType);
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
    }

    private TimeSeries createDataset(List<DateAndCourse> result, String currency) {

        TimeSeries series = new TimeSeries(currency);
        for (int i = 0; i < result.size(); i++) {
            int day = result.get(i).getDate().getDayOfMonth();
            int month =result.get(i).getDate().getMonthValue();
            int year =result.get(i).getDate().getYear();
            series.add(new Day(day, month,year), result.get(i).getCourse());
        }

        return series;
    }

    private JFreeChart createChart(final XYDataset dataset, List<String> numberOfCurr) {

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

        return chart;
    }
}