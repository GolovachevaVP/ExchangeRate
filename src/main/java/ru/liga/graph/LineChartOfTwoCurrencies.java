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

public class LineChartOfTwoCurrencies extends JFrame implements IGraph{

    public void initUI(List<String> numberOfCurr, IPredication predicator, String algorithmType) throws IOException {
        String currencyOneType = numberOfCurr.get(0);
        String currencyTwoType = numberOfCurr.get(1);

        List<DateAndCourse> csvRowsforcurrencyOneType = getCSVRows(currencyOneType);
        List<DateAndCourse> csvRowsforcurrencyTwoType = getCSVRows(currencyTwoType);

        List <DateAndCourse> resultForCurrencyOneType = predicator.rate(csvRowsforcurrencyOneType, algorithmType);
        List <DateAndCourse> resultForCurrencyTwoType = predicator.rate(csvRowsforcurrencyTwoType, algorithmType);

        XYDataset dataset = createDataset(resultForCurrencyOneType,currencyOneType,
                resultForCurrencyTwoType, currencyTwoType);

        JFreeChart chart = createChart(dataset);

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

    private XYDataset createDataset(List <DateAndCourse> resultForCurrencyOneType, String currencyOneType,
                                    List <DateAndCourse> resultForCurrencyTwoType, String currencyTwoType) {

        XYSeries series1 = new XYSeries(currencyOneType);
        for(int i=0; i<resultForCurrencyOneType.size(); i++){
            series1.add(i, resultForCurrencyOneType.get(i).getCourse());
        }

        XYSeries series2 = new XYSeries(currencyTwoType);
        for(int i=0; i<resultForCurrencyTwoType.size(); i++){
            series2.add(i, resultForCurrencyTwoType.get(i).getCourse());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset) {

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

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

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



