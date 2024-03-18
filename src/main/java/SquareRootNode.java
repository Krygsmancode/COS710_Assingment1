public class SquareRootNode extends Node {

    public SquareRootNode(Node child) {
        addChild(child); // Add the child to the children list
    }

    @Override
    public double evaluate(double[] features) {
        return Math.sqrt(children.get(0).evaluate(features));
    }

    @Override
    public Node clone() {
        return new SquareRootNode(children.get(0).clone());
    }

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public String toString() {
        return "sqrt(" + children.get(0).toString() + ")";
    }
}
