public class PrintNode extends Node {
    Node valueNode;

    public PrintNode(Node valueNode) {
        this.valueNode = valueNode;
    }

    @Override
    public void act() {
        Integer newValue = getIntValue();
        System.out.println(newValue);
    }

    @Override
    public Integer getIntValue() {
        return valueNode.getIntValue();
    }

    @Override
    public boolean getBooleanValue() {
        return true;
    }

    public String toString() {
        return "print [" + valueNode + "]";
    }
}
