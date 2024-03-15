public class EqualNode extends Node {

    public EqualNode(Node leftChild, Node rightChild) {
        children.add(leftChild);
        children.add(rightChild);
    }

    @Override
    public double evaluate(double[] features) {
        return children.get(0).evaluate(features) == children.get(1).evaluate(features) ? 1.0 : 0.0;
    }

    @Override
    public Node clone() {
        return new EqualNode(children.get(0).clone(), children.get(1).clone());
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String toString() {
        return "(" + children.get(0).toString() + " = " + children.get(1).toString() + ")";
    }
}
