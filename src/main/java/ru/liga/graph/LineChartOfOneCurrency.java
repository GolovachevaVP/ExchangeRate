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
import ru.liga.algorithm.IAlgorithm;
import ru.liga.dto.DateAndCourse;
import ru.liga.predication.IPredication;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static ru.liga.utils.CSVReader.getCSVRows;

public class LineChartOfOneCurrency extends JFrame implements IGraph {
//    public LineChartOfOneCurrency() {
//        initUI();
//    }

    public void initUI(List<String> numberOfCurr, IPredication predicator, String algorithmType) throws IOException {
        String currencyType = numberOfCurr.get(0);
        List<DateAndCourse> csvRows = getCSVRows(currencyType);
        List <DateAndCourse> result = predicator.rate(csvRows, algorithmType);

        XYDataset dataset = createDataset(result, currencyType);
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

    private XYDataset createDataset(List <DateAndCourse> result,  String currencyType) {

        XYSeries series = new XYSeries(currencyType);
        for(int i=0; i<result.size(); i++){
            series.add(i, result.get(i).getCourse());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

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

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Currency forecast",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

}

