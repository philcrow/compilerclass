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
        return "move " + value + ".0 " + register + "\n";
    }

    @Override
    public Integer getIntValue() {
        return value;
    }

    public String toString() {
        return "INT " + value;
    }
}
