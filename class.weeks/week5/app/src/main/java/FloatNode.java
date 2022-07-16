public class FloatNode extends Node {
    Double value;

    public FloatNode(int lineNumber, Double value) {
        super(lineNumber);
        this.value = value;
    }

    @Override
    public void act() { }

    @Override
    public boolean canBeInt() {
        return false;
    }

    @Override
    public Integer getIntValue() {
        throw new RuntimeException("cannot use float as int");
    }

    @Override
    public Double getFloatValue() {
        return value;
    }

    @Override
    public boolean getBooleanValue() {
        throw new RuntimeException("cannot use float to get to the truth");
    }

    public String toString() {
        return "FLOAT " + value;
    }
}
