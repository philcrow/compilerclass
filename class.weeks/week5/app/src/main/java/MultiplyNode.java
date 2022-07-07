public class MultiplyNode extends Node {
    Node left;
    Node right;

    public MultiplyNode(Node left, Node right) {
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
            return left.getIntValue() * right.getIntValue();
        }
        else {
            throw new RuntimeException("Attempt to perform int multiply with float(s)");
        }
    }

    @Override
    public Double getFloatValue() {
        return left.getFloatValue() * right.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "multiply " + left + " * " + right;
    }
}
