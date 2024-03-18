import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;

public class FitnessPlotter {
    private static XYSeries series = new XYSeries("Fitness Over Generations");
    private static XYSeriesCollection dataset = new XYSeriesCollection(series);
    private static JFreeChart chart = ChartFactory.createXYLineChart(
            "Fitness Over Generations",
            "Generation",
            "Fitness",
            dataset
    );
    private static ChartPanel chartPanel = new ChartPanel(chart);
    private static JFrame frame = new JFrame("Fitness Plot");

    static {
        // Customizing the y-axis range
        XYPlot plot = chart.getXYPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.70, 0.81); // Set the desired range for the y-axis

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void updateFitnessPlot(int generation, double fitness) {
        series.add(generation, fitness);
    }
}
