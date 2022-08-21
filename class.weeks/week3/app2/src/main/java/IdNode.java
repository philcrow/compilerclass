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
    public String emit(String register) {
        return "move " + symbol + " " + register + "\n";
    }

    @Override
    public Integer getIntValue() {
        return visitor.resolve(symbol);
    }

    public String toString() {
        return "id " + symbol;
    }
}
