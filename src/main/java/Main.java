import org.apache.commons.math3.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Create a primitive set with the operations and terminals
        PrimitiveSet primitiveSet = new PrimitiveSet();

        // Set the mutation probability, maximum depth for the trees, population size, and random seed
        double mutationProb = 0.5;
        int maxDepth = 10;
        int populationSize = 10000;
        int randomSeed = 42;
        int tournamentSize = 3;
        double crossoverProb = 0.7;
        int genAmount = 5000;

        // Create a population initializer and fitness evaluator
        PopulationInitialiser populationInitialiser = new PopulationInitialiser(primitiveSet, maxDepth, randomSeed);
        String datasetPath = "/Users/frankoswanepoel/Desktop/2024/COS 710/Assingments/processed_diabetes.csv";
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(datasetPath);

        // Generate the initial population and evaluate its fitness
        List<Individual> population = populationInitialiser.initialisePopulation(populationSize);
        fitnessEvaluator.evaluatePopulation(population);


        // Create a Random object for generating random numbers, a Mutation object, and a Crossover object
        Random random = new Random(randomSeed);
        Mutation mutation = new Mutation(primitiveSet, mutationProb, maxDepth, randomSeed);
        Crossover crossover = new Crossover(randomSeed, maxDepth);

        // List to store the best fitness scores in each generation
        List<Double> bestFitnessScores = new ArrayList<>();

        // Iterate for a certain number of generations
        for (int generation = 0; generation < genAmount; generation++) {
            System.out.println("Generation " + (generation + 1) + ":");

            // Tournament selection
            TournamentSelection tournamentSelection = new TournamentSelection(tournamentSize, randomSeed);
            List<Individual> parents = tournamentSelection.select(population, populationSize);

            // Crossover and mutation
            List<Individual> offspring = new ArrayList<>();
            for (int i = 0; i < parents.size(); i += 2) {
                Individual parent1 = parents.get(i);
                Individual parent2 = parents.get(i + 1);

                if (random.nextDouble() < crossoverProb) {
                    // Perform crossover using the Crossover class
                    crossover.crossover(parent1, parent2, offspring);
                } else {
                    // No crossover, just add the parents to the offspring list
                    offspring.add(parent1.clone());
                    offspring.add(parent2.clone());
                }
            }
            // After crossover and mutation



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

            // Store the best fitness score
            bestFitnessScores.add(bestIndividual.getFitness());

            System.out.println("Best Individual: " + bestIndividual.getRoot());
            System.out.println("Fitness: " + bestIndividual.getFitness());
            System.out.println();
        }

        // Plot the fitness scores over generations
        FitnessPlotter.plotFitness(bestFitnessScores);
    }
}
