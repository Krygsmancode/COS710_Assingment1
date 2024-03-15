public class MultiplyNode extends Node {

    public MultiplyNode(Node leftChild, Node rightChild) {
        addChild(leftChild);
        addChild(rightChild);
    }

    @Override
    public double evaluate(double[] features) {
        return children.get(0).evaluate(features) * children.get(1).evaluate(features);
    }

    @Override
    public Node clone() {
        return new MultiplyNode(children.get(0).clone(), children.get(1).clone());
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String toString() {
        return "(" + children.get(0).toString() + " * " + children.get(1).toString() + ")";
    }
}
