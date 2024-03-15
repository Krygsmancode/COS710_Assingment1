public class LessThanNode extends Node {

    public LessThanNode(Node leftChild, Node rightChild) {
        addChild(leftChild);
        addChild(rightChild);
    }

    @Override
    public double evaluate(double[] features) {
        return children.get(0).evaluate(features) < children.get(1).evaluate(features) ? 1.0 : 0.0;
    }

    @Override
    public Node clone() {
        return new LessThanNode(children.get(0).clone(), children.get(1).clone());
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String toString() {
        return "(" + children.get(0).toString() + " < " + children.get(1).toString() + ")";
    }
}
