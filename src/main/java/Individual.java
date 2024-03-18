
public class Individual extends Node {
    private Node root;  // Root node of the tree
    private double fitness;

    public Individual(Node root) {
        this.root = root;
        this.fitness = 0.0;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // Additional methods for evaluating the tree, cloning the individual, etc.

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double evaluate(double[] features) {
        return root.evaluate(features);
    }

    public double getFitness()
    {
        return fitness;
    }

    @Override
    public Individual clone() {
        // Create a new Individual instance and clone the root node
        Individual cloned = new Individual(this.root.clone());
        cloned.setFitness(this.fitness); // Copy the fitness value
        return cloned;
    }

        @Override
        public int getArity() {
            return 0;
        }

        @Override
        public String toString() {
            return null;
        }

}

