import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.util.List;

public class FitnessPlotter {
    public static void plotFitness(List<Double> fitnessValues) {
        XYSeries series = new XYSeries("Fitness Over Generations");
        for (int i = 0; i < fitnessValues.size(); i++) {
            series.add(i, fitnessValues.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Fitness Over Generations",
                "Generation",
                "Fitness",
                dataset
        );

        // Customizing the y-axis range
        XYPlot plot = chart.getXYPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.5, 1.0); // Set the desired range for the y-axis

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fitness Plot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ChartPanel(chart));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
