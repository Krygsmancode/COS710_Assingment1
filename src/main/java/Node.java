import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Node implements Cloneable {
    protected List<Node> children;
    protected Node parent; // Add a parent reference

    public Node() {
        this.children = new ArrayList<>();
        this.parent = null; // Initialize parent as null
    }

    public void addChild(Node child) {
        children.add(child);
        child.parent = this; // Set the parent of the child
    }

    public List<Node> getChildren() {
        return children;
    }

    public abstract double evaluate(double[] features);

    @Override
    public abstract Node clone();

    protected List<Node> cloneChildren() {
        List<Node> clonedChildren = new ArrayList<>();
        for (Node child : children) {
            clonedChildren.add(child.clone());
        }
        return clonedChildren;
    }

    public abstract int getArity();

    public abstract String toString();

    // Get a random node from the subtree rooted at this node
    public Node getRandomNode(Random random) {
        List<Node> allNodes = new ArrayList<>();
        collectAllNodes(this, allNodes);
        return allNodes.get(random.nextInt(allNodes.size()));
    }

    private void collectAllNodes(Node node, List<Node> allNodes) {
        allNodes.add(node);
        for (Node child : node.getChildren()) {
            collectAllNodes(child, allNodes);
        }
    }
    public static int getDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int maxChildDepth = 0;
        for (Node child : node.getChildren()) {
            maxChildDepth = Math.max(maxChildDepth, getDepth(child));
        }
        return 1 + maxChildDepth;
    }

    // Replace this node with another node in its parent's children list
    public void replaceWith(Node newNode) {
        if (this.parent != null) {
            int index = this.parent.children.indexOf(this);
            if (index != -1) {
                this.parent.children.set(index, newNode);
                newNode.parent = this.parent;
            }
        }
    }


}
