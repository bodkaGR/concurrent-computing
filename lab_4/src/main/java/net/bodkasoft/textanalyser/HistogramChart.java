package net.bodkasoft.textanalyser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class HistogramChart extends JFrame {
    public HistogramChart(Map<Integer, Integer> data) {
        setTitle("Гістограма частот");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Integer, Integer> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Частота", entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Гістограма частот",
                "Довжина слова",
                "Частота",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        setContentPane(chartPanel);
    }
}
