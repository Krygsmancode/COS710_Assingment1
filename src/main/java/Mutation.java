import java.util.List;
import java.util.Random;

public class Mutation {
    private final PrimitiveSet primitiveSet;
    private final Random random;
    private double mutationProb; // Make this non-final to allow changes
    private final int maxDepth;

    public Mutation(PrimitiveSet primitiveSet, double mutationProb, int maxDepth, int randomSeed) {
        this.primitiveSet = primitiveSet;
        this.mutationProb = mutationProb;
        this.maxDepth = maxDepth;
        this.random = new Random(randomSeed);
    }

    // Method to set the mutation probability
    public void setMutationProb(double newMutationProb) {
        this.mutationProb = newMutationProb;
    }

    public void mutatePopulation(List<Individual> population) {
        for (Individual individual : population) {
            if (random.nextDouble() < mutationProb) {
                mutateIndividual(individual);
            }
        }
    }

    private void mutateIndividual(Individual individual) {
        // Select a random node in the tree
        Node mutationPoint = individual.getRoot().getRandomNode(random);

        // Calculate the remaining depth for the new subtree
        int currentDepth = Node.getDepth(individual.getRoot()) - Node.getDepth(mutationPoint);
        int remainingDepth = maxDepth - currentDepth;

        // Generate a new subtree with the remaining depth limit
        Node newSubtree = growRandomTree(0, remainingDepth);
        mutationPoint.replaceWith(newSubtree);
    }

    private Node growRandomTree(int currentDepth, int remainingDepth) {
        if (currentDepth >= remainingDepth || random.nextBoolean()) {
            // Grow a terminal node (feature or constant)
            return primitiveSet.getRandomTerminal().clone();
        } else {
            // Grow an internal node (function node)
            Class<? extends Node> nodeClass = primitiveSet.getRandomOperation();
            try {
                Node node;
                if (nodeClass == SquareRootNode.class || nodeClass == LogarithmNode.class) {
                    // Handle unary node separately
                    Node child = growRandomTree(currentDepth + 1, remainingDepth - 1);
                    node = nodeClass.getDeclaredConstructor(Node.class).newInstance(child);
                } else {
                    // Handle binary nodes by creating two children
                    Node leftChild = growRandomTree(currentDepth + 1, remainingDepth - 1);
                    Node rightChild = growRandomTree(currentDepth + 1, remainingDepth - 1);
                    node = nodeClass.getDeclaredConstructor(Node.class, Node.class).newInstance(leftChild, rightChild);
                }
                return node;
            } catch (Exception e) {
                throw new RuntimeException("Failed to instantiate node class: " + nodeClass.getName(), e);
            }
        }
    }
}
