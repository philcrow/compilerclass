public class IntNode extends Node {
    Integer value;

    public IntNode(Integer value) {
        this.value = value;
    }

    @Override
    public void act() {
    }

    @Override
    public Integer getIntValue() {
        return value;
    }

    public String toString() {
        return "INT " + value;
    }
}
