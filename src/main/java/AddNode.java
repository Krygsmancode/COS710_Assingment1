public class AddNode extends Node {

    public AddNode(Node leftChild, Node rightChild) {
        children.add(leftChild);
        children.add(rightChild);
    }

    @Override
    public double evaluate(double[] features) {
        return children.get(0).evaluate(features) + children.get(1).evaluate(features);
    }

    @Override
    public Node clone() {
        // Ensure that both children are cloned
        Node clonedLeftChild = children.get(0).clone();
        Node clonedRightChild = children.get(1).clone();
        return new AddNode(clonedLeftChild, clonedRightChild);
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String toString() {
        return "(" + children.get(0).toString() + " + " + children.get(1).toString() + ")";
    }
}
