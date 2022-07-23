public class IntNode extends Node {
    Integer value;

    public IntNode(Integer value) {
        this.value = value;
    }

    @Override
    public void act() {
    }

    @Override
    public String emit(String register) {
        return "movl $" + value + ", " + register + "\n";
    }

    @Override
    public Integer getIntValue() {
        return value;
    }

    public String toString() {
        return "INT " + value;
    }
}
