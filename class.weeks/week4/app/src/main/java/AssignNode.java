public class AssignNode extends Node {
    String symbol;
    Node valueNode;
    EvalVisitor visitor;

    public AssignNode(String symbol, Node valueNode, EvalVisitor visitor) {
        this.symbol = symbol;
        this.valueNode = valueNode;
        this.visitor = visitor;
    }

    @Override
    public void act() {
        Integer newValue = getIntValue();
        visitor.set(symbol, newValue);
    }

    @Override
    public Integer getIntValue() {
        return valueNode.getIntValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "assign " + symbol + " " + valueNode;
    }
}
