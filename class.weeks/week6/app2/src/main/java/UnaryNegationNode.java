public class UnaryNegationNode extends Node {
    Node value;

    public UnaryNegationNode(Node value) {
        this.value = value;
    }

    @Override
    public void act() { }

    @Override
    public boolean canBeInt() {
        return value.canBeInt();
    }

    @Override
    public Integer getIntValue() {
        return -1 * value.getIntValue();
    }

    @Override
    public Double getFloatValue() {
        return -1 * value.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "NEGATED " + value;
    }
}
