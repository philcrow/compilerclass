public class AssignNode extends Node {
    String name;
    Node valueNode;
    EvalVisitor visitor;

    public AssignNode(String name, Node valueNode, EvalVisitor visitor) {
        this.name = name;
        this.valueNode = valueNode;
        this.visitor = visitor;
    }

    @Override
    public void act() {
        Symbol symbol = visitor.resolve(name);
        // make sure the types match
        if (canBeInt()) {
            if (valueNode.canBeInt()) {
                symbol.setIntValue(valueNode.getIntValue());
            }
            else {
                throw new RuntimeException("cannot assign float to int " + name);
            }
        }
        else { // symbol is a float
            symbol.setFloatValue(valueNode.getFloatValue());
        }
        Integer newValue = getIntValue();
        visitor.setValue(name, newValue);
    }

    @Override
    public boolean canBeInt() {
        Symbol symbol = visitor.resolve(name);
        return symbol.type == Symbol.SymbolType.INT;
    }

    @Override
    public Integer getIntValue() {
        Symbol symbol = visitor.resolve(name);
        if (canBeInt()) {
            return valueNode.getIntValue();
        }
        else {
            throw new RuntimeException("cannot get int from float " + name);
        }
    }

    @Override
    public Double getFloatValue() {
        return valueNode.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "assign " + name + " " + valueNode;
    }
}
