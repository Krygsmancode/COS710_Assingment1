
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimitiveSet {
    private List<Class<? extends Node>> operations;
    private List<Node> terminals;  // Changed to hold Node instances directly
    private Random random;

    public PrimitiveSet() {
        operations = new ArrayList<>();
        terminals = new ArrayList<>();
        random = new Random(42);
        initialise();
    }

    private void initialise() {
        // Add operation node types
        operations.add(AddNode.class);
        operations.add(SubtractNode.class);
        operations.add(MultiplyNode.class);
        operations.add(DivideNode.class);
        operations.add(GreaterThanNode.class);
        operations.add(LessThanNode.class);
        operations.add(EqualNode.class);
        operations.add(SquareRootNode.class);
        operations.add(LogarithmNode.class);




        // Add terminal nodes for each feature
        terminals.add(new FeatureNode("Pregnancies",0)); // Pregnancies
        terminals.add(new FeatureNode("Glucose",1)); // Glucose
        terminals.add(new FeatureNode("BloodPressure",2)); // BloodPressure
        terminals.add(new FeatureNode("SkinThickness",3)); // SkinThickness
        terminals.add(new FeatureNode("Insulin",4)); // Insulin
        terminals.add(new FeatureNode("BMI",5)); // BMI
        terminals.add(new FeatureNode("DiabetesPedigreeFunction",6)); // DiabetesPedigreeFunction
        terminals.add(new FeatureNode("Age",7)); // Age

        // Add constant node type
//       terminals.add(new ConstantNode(random.nextDouble() * 10));  // Example constant value
    }

    public List<Class<? extends Node>> getOperations() {
        return operations;
    }

    public List<Node> getTerminals() {
        return terminals;
    }

    public Class<? extends Node> getRandomOperation() {
        return operations.get(random.nextInt(operations.size()));
    }

    public Node getRandomTerminal() {
        // 10% chance to generate a ConstantNode with a random value
        if (random.nextDouble() < 0.1) {
            return new ConstantNode(random.nextDouble() * 40 - 20);
        }
        return terminals.get(random.nextInt(terminals.size())).clone();
    }

}
