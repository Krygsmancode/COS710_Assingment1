import org.apache.commons.math3.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Create a primitive set with the operations and terminals
        PrimitiveSet primitiveSet = new PrimitiveSet();

        // Set initial and final probabilities
        double initialMutationProb = 0.9;
        double finalMutationProb = 0.001;
        double initialCrossoverProb = 0.9;
        double finalCrossoverProb = 0.3;

        // Set other parameters
        int maxDepth = 8;
        int populationSize = 1000;
        int randomSeed = 42;
        int tournamentSize = 2;
        double mutationProb = initialMutationProb; // Start with initial probability
        double crossoverProb; // Start with initial probability
        int genAmount = 100000;

        // Create a population initializer and fitness evaluator
        PopulationInitialiser populationInitialiser = new PopulationInitialiser(primitiveSet, maxDepth, randomSeed);
        String datasetPath = "/Users/frankoswanepoel/Desktop/2024/COS 710/Assingments/processed_diabetes.csv";
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(datasetPath);

        List<Individual> population = populationInitialiser.initialisePopulation(populationSize);
        fitnessEvaluator.evaluatePopulation(population);

        // Create a Random object for generating random numbers
        Random random = new Random(randomSeed);

        // Initialize crossover and mutation outside the loop, as they do not need to be recreated each time
        Mutation mutation = new Mutation(primitiveSet, mutationProb, maxDepth, randomSeed);
        Crossover crossover = new Crossover(randomSeed, maxDepth);

        // List to store the best fitness scores in each generation
        List<Double> bestFitnessScores = new ArrayList<>();

        // Main loop for genetic algorithm
        for (int generation = 0; generation < genAmount; generation++) {
            // Dynamically adjust probabilities
            mutationProb = initialMutationProb + (finalMutationProb - initialMutationProb) * generation / genAmount;
            crossoverProb = initialCrossoverProb + (finalCrossoverProb - initialCrossoverProb) * generation / genAmount;
            mutation.setMutationProb(mutationProb); // Update the mutation object's probability

            System.out.println("Generation " + (generation + 1) + ":");
            System.out.println("Current mutation probability: " + mutationProb);
            System.out.println("Current crossover probability: " + crossoverProb);

            // Tournament selection
            TournamentSelection tournamentSelection = new TournamentSelection(tournamentSize, randomSeed);
            List<Individual> parents = tournamentSelection.select(population, populationSize);

            // Crossover and mutation
            List<Individual> offspring = new ArrayList<>();
            for (int i = 0; i < parents.size(); i += 2) {
                Individual parent1 = parents.get(i);
                Individual parent2 = parents.get(i + 1);

                // Perform crossover using the Crossover class
                if (random.nextDouble() < crossoverProb) {
                    // Perform crossover using the Crossover class
                    crossover.crossover(parent1, parent2, offspring);
                } else {
                    // No crossover, just add the parents to the offspring list
                    offspring.add(parent1.clone());
                    offspring.add(parent2.clone());
                }
            }

            mutation.mutatePopulation(offspring);

            // Evaluate the fitness of the new offspring
            fitnessEvaluator.evaluatePopulation(offspring);

            // Select the next generation
            population = tournamentSelection.select(population, populationSize / 2);
            population.addAll(tournamentSelection.select(offspring, populationSize / 2));

            // Find the best individual in the current generation
            Individual bestIndividual = population.stream()
                    .max((ind1, ind2) -> Double.compare(ind1.getFitness(), ind2.getFitness()))
                    .orElseThrow(() -> new RuntimeException("Failed to find the best individual"));

            bestFitnessScores.add(bestIndividual.getFitness());
            // Assuming updateFitnessPlot() method updates the chart in real-time
            FitnessPlotter.updateFitnessPlot(generation + 1, bestIndividual.getFitness());

            System.out.println("Best Individual: " + bestIndividual.getRoot());
            System.out.println("Fitness: " + bestIndividual.getFitness());
            System.out.println();
        }

        // The plotting part will update after each generation within the updateFitnessPlot call
    }
}
