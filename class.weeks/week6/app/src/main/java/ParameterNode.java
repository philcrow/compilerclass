public class ParameterNode extends Node {
    Symbol symbol;

    public ParameterNode(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public void act() { }

    @Override
    public boolean canBeInt() {
        return symbol.getType() == Symbol.SymbolType.INT;
    }

    @Override
    public Integer getIntValue() {
        return symbol.getIntValue();
    }

    @Override
    public Double getFloatValue() {
        return symbol.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return false; // this is not meaningful
    }

    public String toString() {
        return symbol.toString();
    }
}
