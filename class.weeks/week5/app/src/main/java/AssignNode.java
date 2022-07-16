public class AssignNode extends Node {
    String name;
    Node valueNode;
    EvalVisitor visitor;

    public AssignNode(int lineNumber, String name, Node valueNode, EvalVisitor visitor) {
        super(lineNumber);
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
                try {
                    symbol.setIntValue(valueNode.getIntValue());
                }
                catch (Exception e) {
                    String message = e.getMessage() + " at line " + getLineNumber();
                    throw new RuntimeException(message);
                }
            }
            else {
                throw new RuntimeException("cannot assign float to int " + name + " at line " + getLineNumber());
            }
        }
        else { // symbol is a float
            try {
                symbol.setFloatValue(valueNode.getFloatValue());
            }
            catch (Exception e) {
                String message = e.getMessage() + " at line " + getLineNumber();
                throw new RuntimeException(message);
            }
        }
    }

    @Override
    public boolean canBeInt() {
        Symbol symbol = visitor.resolve(name);
        return symbol.type == Symbol.SymbolType.INT;
    }

    @Override
    public Integer getIntValue() {
        // this is not meaningfully called
        return null;
    }

    @Override
    public Double getFloatValue() {
        // this is not meaningfully called
        return null;
    }

    @Override
    public boolean getBooleanValue() {
        // this is not meaningfully called
        return false;
    }

    public String toString() {
        return "assign " + name + " " + valueNode;
    }
}
