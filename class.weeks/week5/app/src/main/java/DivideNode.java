public class DivideNode extends Node {
    Node left;
    Node right;

    public DivideNode(int lineNumber, Node left, Node right) {
        super(lineNumber);
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
            return left.getIntValue() / right.getIntValue();
        }
        else {
            // prevented by AssignNode
            throw new RuntimeException("Attempt to perform int division with float(s)");
        }
    }

    @Override
    public Double getFloatValue() {
        return left.getFloatValue() / right.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "divide " + left + " / " + right;
    }
}
