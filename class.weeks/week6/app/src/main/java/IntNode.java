public class IntNode extends Node {
    Integer value;

    public IntNode(Integer value) {
        this.value = value;
    }

    @Override
    public void act() { }

    @Override
    public boolean canBeInt() {
        return true;
    }

    @Override
    public Integer getIntValue() {
        return value;
    }

    @Override
    public Double getFloatValue() {
        return Double.valueOf(value);
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "INT " + value;
    }
}
