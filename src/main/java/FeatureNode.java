public class FeatureNode extends Node {
    private String featureName;
    private int featureIndex;

    public FeatureNode(String featureName, int featureIndex) {
        this.featureName = featureName;
        this.featureIndex = featureIndex;
    }

    @Override
    public int getArity() {
        return 0;  // Terminal node has no children
    }

    @Override
    public String toString() {
        return featureName;
    }

    @Override
    public double evaluate(double[] features) {
        return features[featureIndex];
    }

    @Override
    public Node clone() {
        return new FeatureNode(featureName, featureIndex);
    }
}
