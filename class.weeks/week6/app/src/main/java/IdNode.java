public class IdNode extends Node {
    String name;
    EvalVisitor visitor;

    public IdNode(String name, EvalVisitor visitor) {
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
            throw new RuntimeException("Symbol " + name + " is an int not a float");
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
