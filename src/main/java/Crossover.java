import java.util.List;
import java.util.Random;

public class Crossover {
    private Random random;
    private int maxDepth;

    public Crossover(int randomSeed, int maxDepth) {
        this.random = new Random(randomSeed);
        this.maxDepth = maxDepth;
    }

    public void crossover(Individual parent1, Individual parent2, List<Individual> offspring) {
        // Clone the parents to create offspring
        Individual offspring1 = parent1.clone();
        Individual offspring2 = parent2.clone();

        // Select random nodes in each offspring
        Node randomNode1 = offspring1.getRoot().getRandomNode(random);
        Node randomNode2 = offspring2.getRoot().getRandomNode(random);

        // Check if swapping subtrees would exceed the maximum depth
        if (Node.getDepth(offspring1.getRoot()) - Node.getDepth(randomNode1) + Node.getDepth(randomNode2) <= maxDepth &&
                Node.getDepth(offspring2.getRoot()) - Node.getDepth(randomNode2) + Node.getDepth(randomNode1) <= maxDepth) {
            // Swap the subtrees
            Node temp = randomNode1.clone();
            randomNode1.replaceWith(randomNode2.clone());
            randomNode2.replaceWith(temp);
        }

        // Add the offspring to the list
        offspring.add(offspring1);
        offspring.add(offspring2);
    }
}
