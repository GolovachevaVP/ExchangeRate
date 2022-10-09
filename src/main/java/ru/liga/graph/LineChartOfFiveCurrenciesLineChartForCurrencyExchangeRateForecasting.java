package ru.liga.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.liga.dto.DateAndCourse;
import ru.liga.predication.IPredication;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static ru.liga.utils.CSVReader.getCSVRows;

public class LineChartOfFiveCurrenciesLineChartForCurrencyExchangeRateForecasting extends JFrame implements IGraph{
    public void initUI(List<String> numberOfCurr, IPredication predicator, String algorithmType) throws IOException {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for(String currencyType:numberOfCurr){
            String curr = currencyType;
            List<DateAndCourse> csvRows = getCSVRows(curr);
            List <DateAndCourse> result = predicator.rate(csvRows, algorithmType);
             dataset.addSeries(createDataset(result, curr));
        }
//        String currencyOneType = numberOfCurr.get(0);
//        String currencyTwoType = numberOfCurr.get(1);
//        String currencyThreeType = numberOfCurr.get(2);
//        String currencyFourType = numberOfCurr.get(3);
//        String currencyFiveType = numberOfCurr.get(4);
//
//        List<DateAndCourse> csvRowsforcurrencyOneType = getCSVRows(currencyOneType);
//        List<DateAndCourse> csvRowsforcurrencyTwoType = getCSVRows(currencyTwoType);
//        List<DateAndCourse> csvRowsforcurrencyThreeType = getCSVRows(currencyThreeType);
//        List<DateAndCourse> csvRowsforcurrencyFourType = getCSVRows(currencyFourType);
//        List<DateAndCourse> csvRowsforcurrencyFiveType = getCSVRows(currencyFiveType);
//
//        List <DateAndCourse> resultForCurrencyOneType = predicator.rate(csvRowsforcurrencyOneType, algorithmType);
//        List <DateAndCourse> resultForCurrencyTwoType = predicator.rate(csvRowsforcurrencyTwoType, algorithmType);
//        List <DateAndCourse> resultForCurrencyThreeType = predicator.rate(csvRowsforcurrencyThreeType, algorithmType);
//        List <DateAndCourse> resultForCurrencyFourType = predicator.rate(csvRowsforcurrencyFourType, algorithmType);
//        List <DateAndCourse> resultFiveCurrencyFiveType = predicator.rate(csvRowsforcurrencyFiveType, algorithmType);
//
//        XYDataset dataset = createDataset(resultForCurrencyOneType,currencyOneType,
//                resultForCurrencyTwoType, currencyTwoType,
//                resultForCurrencyThreeType, currencyThreeType,
//                resultForCurrencyFourType, currencyFourType,
//                resultFiveCurrencyFiveType, currencyFiveType);

        JFreeChart chart = createChart(dataset,numberOfCurr);
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

    private XYSeries createDataset(List <DateAndCourse> result, String currency) {

        XYSeries series = new XYSeries(currency);
        for(int i=0; i<=result.size(); i++){
            series.add(i, result.get(i).getCourse());
        }
//
//        XYSeries series2 = new XYSeries(currencyTwoType);
//        for(int i=0; i<resultForCurrencyTwoType.size(); i++){
//            series2.add(i, resultForCurrencyTwoType.get(i).getCourse());
//        }
//
//        XYSeries series3 = new XYSeries(currencyThreeType);
//        for(int i=0; i<resultForCurrencyThreeType.size(); i++){
//            series3.add(i, resultForCurrencyThreeType.get(i).getCourse());
//        }
//
//        XYSeries series4 = new XYSeries(currencyFourType);
//        for(int i=0; i<resultForCurrencyFourType.size(); i++){
//            series4.add(i, resultForCurrencyFourType.get(i).getCourse());
//        }
//
//        XYSeries series5 = new XYSeries(currencyFiveType);
//        for(int i=0; i<resultForCurrencyFiveType.size(); i++){
//            series5.add(i, resultForCurrencyFiveType.get(i).getCourse());
//        }

//        XYSeriesCollection dataset = new XYSeriesCollection();
//        dataset.addSeries(series);
//        dataset.addSeries(series2);
//        dataset.addSeries(series3);
//        dataset.addSeries(series4);
//        dataset.addSeries(series5);

        return series;
    }

    private JFreeChart createChart(final XYDataset dataset,List<String> numberOfCurr ) {

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

        for(int i=0; i<numberOfCurr.size();i++){
            Color color = new Color((int)(Math.random()*0x1000000));
            renderer.setSeriesPaint(i, color);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }

//        renderer.setSeriesPaint(0, Color.RED);
//        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
//        renderer.setSeriesPaint(1, Color.BLUE);
//        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
//        renderer.setSeriesPaint(2, Color.GREEN);
//        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
//        renderer.setSeriesPaint(3, Color.MAGENTA);
//        renderer.setSeriesStroke(3, new BasicStroke(2.0f));
//        renderer.setSeriesPaint(3, Color.ORANGE);
//        renderer.setSeriesStroke(3, new BasicStroke(2.0f));

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

        return chart;
    }
}