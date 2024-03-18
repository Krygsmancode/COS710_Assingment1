public class DivideNode extends Node {


    public DivideNode(Node leftChild, Node rightChild) {
        children.add(leftChild);
        children.add(rightChild);
    }

    @Override
    public double evaluate(double[] features) {
        double denominator = children.get(1).evaluate(features);
        return denominator != 0 ? children.get(0).evaluate(features) / denominator : 0;  // Avoid division by zero
    }

    @Override
    public Node clone() {
        return new DivideNode(children.get(0).clone(), children.get(1).clone());
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String toString() {
        return "(" + children.get(0).toString() + " / " + children.get(1).toString() + ")";
    }
}
