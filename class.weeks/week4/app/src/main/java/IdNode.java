public class IdNode extends Node {
    String symbol;
    EvalVisitor visitor;

    public IdNode(String symbol, EvalVisitor visitor) {
        this.symbol = symbol;
        this.visitor = visitor;
    }

    @Override
    public void act() {
    }

    @Override
    public Integer getIntValue() {
        return visitor.resolve(symbol);
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "id " + symbol;
    }
}
