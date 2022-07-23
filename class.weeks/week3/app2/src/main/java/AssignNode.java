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
    public String emit(String ignoredRegister) {
        act();
        String register = visitor.getFreeRegister();
        String childEmission = valueNode.emit(register);
        String myEmission = "movl " + register + ", " + symbol + "\n";
        visitor.freeRegister(register);
        return childEmission + myEmission;
    }

    @Override
    public Integer getIntValue() {
        return valueNode.getIntValue();
    }

    public String toString() {
        return "assign " + symbol + " " + valueNode;
    }
}
