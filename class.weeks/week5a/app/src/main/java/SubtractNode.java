public class SubtractNode extends Node {
    Node left;
    Node right;

    public SubtractNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void act() {
    }

    @Override
    public Integer getIntValue() {
        return left.getIntValue() - right.getIntValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "subtract " + left + " - " + right;
    }
}
