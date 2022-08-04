public class ReturnNode extends Node {
    // the name of the return symbol is chosen to be an illegal identifier
    public static final String RETURN_SYMBOL = "1_return_symbol";
    Node valueNode;
    EvalVisitor visitor;

    public ReturnNode(Node valueNode, EvalVisitor visitor) {
        this.valueNode = valueNode;
        this.visitor = visitor;
    }

    @Override
    public void act() {
        Integer intValue = getIntValue();
        visitor.set(RETURN_SYMBOL, intValue);
        throw new ReturnEncounteredException();
    }

    @Override
    public Integer getIntValue() {
        return valueNode.getIntValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }
}
