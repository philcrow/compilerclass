public class ReturnNode extends Node {
    // the name of the return symbol is chose to be an illegal identifier
    public static final String RETURN_SYMBOL = "1_return_symbol";
    Node valueNode;
    EvalVisitor interpretter;

    public ReturnNode(Node valueNode, EvalVisitor interpretter) {
        this.valueNode = valueNode;
        this.interpretter = interpretter;
    }

    @Override
    public void act() {
        Symbol symbol = null;
        if (canBeInt()) {
            Integer intValue = getIntValue();
            symbol = new IntSymbol(RETURN_SYMBOL);
            symbol.setIntValue(intValue);
        }
        else {
            Double floatValue = getFloatValue();
            symbol = new FloatSymbol(RETURN_SYMBOL);
            symbol.setFloatValue(floatValue);
        }
        interpretter.setReturnValue(RETURN_SYMBOL, symbol);

        throw new ReturnEncounteredException();
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
        return "return [" + valueNode + "]";
    }
}
