public class LogarithmNode extends Node {

    public LogarithmNode(Node child) {
        addChild(child); // Add the child to the children list
    }

    @Override
    public double evaluate(double[] features) {
        return Math.log(children.get(0).evaluate(features));
    }

    @Override
    public Node clone() {
        return new LogarithmNode(children.get(0).clone());
    }

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public String toString() {
        return "log(" + children.get(0).toString() + ")";
    }
}
