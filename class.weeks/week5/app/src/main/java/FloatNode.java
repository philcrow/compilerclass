public class FloatNode extends Node {
    Double value;

    public FloatNode(Double value) {
        this.value = value;
    }

    @Override
    public void act() {
    }

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
        return getIntValue() != 0;
    }

    public String toString() {
        return "INT " + value;
    }
}
