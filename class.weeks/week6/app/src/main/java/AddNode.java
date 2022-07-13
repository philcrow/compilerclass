public class AddNode extends Node {
    Node left;
    Node right;

    public AddNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void act() { }

    @Override
    public boolean canBeInt() {
        return left.canBeInt() && right.canBeInt();
    }

    @Override
    public Integer getIntValue() {
        if (canBeInt()) {
            return left.getIntValue() + right.getIntValue();
        }
        else {
            throw new RuntimeException("Attempt to perform int add with float(s)");
        }
    }

    @Override
    public Double getFloatValue() {
        return left.getFloatValue() + right.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "Add " + left + " + " + right;
    }
}
