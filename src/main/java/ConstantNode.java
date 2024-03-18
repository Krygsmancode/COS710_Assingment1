public class ConstantNode extends Node {
    private double value;

    public ConstantNode(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(double[] features) {
        return value;
    }

    @Override
    public Node clone() {
        return new ConstantNode(value);
    }

    @Override
    public int getArity() {
        return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
