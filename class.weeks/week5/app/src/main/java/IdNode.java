public class IdNode extends Node {
    String name;
    EvalVisitor visitor;

    public IdNode(int lineNumber, String name, EvalVisitor visitor) {
        super(lineNumber);
        this.name = name;
        this.visitor = visitor;
    }

    @Override
    public void act() { }

    @Override
    public boolean canBeInt() {
        Symbol symbol = visitor.resolve(name);
        return symbol.type == Symbol.SymbolType.INT;
    }

    @Override
    public Integer getIntValue() {
        if (canBeInt()) {
            Symbol symbol = visitor.resolve(name);
            return symbol.getIntValue();
        }
        else {
            // probably prevented by AssignNode
            throw new RuntimeException("Symbol " + name + " is a float not an int");
        }
    }

    @Override
    public Double getFloatValue() {
        Symbol symbol = visitor.resolve(name);
        return symbol.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "id " + name;
    }
}
