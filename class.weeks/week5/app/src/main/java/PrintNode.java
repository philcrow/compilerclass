public class PrintNode extends Node {
    Node valueNode;

    public PrintNode(int lineNumber, Node valueNode) {
        super(lineNumber);
        this.valueNode = valueNode;
    }

    @Override
    public void act() {
        if (canBeInt()) {
            System.out.println(getIntValue());
        }
        else {
            System.out.println(getFloatValue());
        }
    }

    @Override
    public boolean canBeInt() {
        return valueNode.canBeInt();
    }

    @Override
    public Integer getIntValue() {
        return valueNode.getIntValue();
    }

    @Override
    public Double getFloatValue() {
        return valueNode.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return true;
    }

    public String toString() {
        return "print [" + valueNode + "]";
    }
}
