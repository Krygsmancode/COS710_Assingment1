import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationInitialiser {
    private PrimitiveSet primitiveSet;
    private int maxDepth;
    private Random random;

    public PopulationInitialiser(PrimitiveSet primitiveSet, int maxDepth, int randomSeed) {
        this.primitiveSet = primitiveSet;
        this.maxDepth = maxDepth;
        this.random = new Random(randomSeed);
    }

    public List<Individual> initialisePopulation(int populationSize) {
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Node rootNode = growTree(0);
            population.add(new Individual(rootNode));
        }
        return population;
    }

    private Node growTree(int currentDepth) {
        if (currentDepth >= maxDepth || random.nextBoolean()) {
            return primitiveSet.getRandomTerminal().clone();
        } else {
            Class<? extends Node> nodeClass = primitiveSet.getRandomOperation();
            try {
                Node node;
                if (nodeClass == SquareRootNode.class || nodeClass == LogarithmNode.class) {
                    // Handle unary nodes
                    Node child = growTree(currentDepth + 1);
                    node = nodeClass.getDeclaredConstructor(Node.class).newInstance(child);
                } else {
                    // Handle binary nodes
                    Node leftChild = growTree(currentDepth + 1);
                    Node rightChild = growTree(currentDepth + 1);
                    node = nodeClass.getDeclaredConstructor(Node.class, Node.class).newInstance(leftChild, rightChild);
                }
                return node;
            } catch (Exception e) {
                throw new RuntimeException("Failed to instantiate node class: " + nodeClass.getName(), e);
            }
        }
    }
}
