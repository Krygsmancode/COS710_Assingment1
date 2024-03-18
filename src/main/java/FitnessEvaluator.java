import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FitnessEvaluator {
    private List<double[]> dataset;

    public FitnessEvaluator(String filePath) {
        dataset = loadDataset(filePath);
    }

    private List<double[]> loadDataset(String filePath) {
        List<double[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;  // Skip the first line if it contains headers
                }
                String[] tokens = line.split(",");
                double[] instance = new double[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    instance[i] = Double.parseDouble(tokens[i]);
                }
                data.add(instance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void evaluatePopulation(List<Individual> population) {
        for (Individual individual : population) {
            double accuracy = evaluateIndividual(individual);
            individual.setFitness(accuracy);
        }
    }

    private double evaluateIndividual(Individual individual) {
        int correct = 0;
        for (double[] instance : dataset) {
            double prediction = individual.evaluate(instance);
            if ((prediction >= 0.5 && instance[instance.length - 1] == 1.0) ||
                    (prediction < 0.5 && instance[instance.length - 1] == 0.0)) {
                correct++;
            }
        }
        return (double) correct / dataset.size();
    }

}
