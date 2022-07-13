import java.util.List;

public class FunctionNode extends Node {
    List<Node> parameters;
    String returnType;
    Node block;
    SymbolTable functionSymbols;

    public FunctionNode(List<Node> parameters, String returnType, Node block) {
        this.parameters = parameters;
        this.returnType = returnType;
        this.block = block;
    }

    @Override
    public void act() {
    }

    @Override
    public boolean canBeInt() {
        // TODO use return type to help here
        return false;
    }

    @Override
    public Integer getIntValue() {
        return null;
    }

    @Override
    public Double getFloatValue() {
        return null;
    }

    @Override
    public boolean getBooleanValue() {
        return false;
    }

    public String toString() {
        return "function (" + parameters + ")" + block;
    }
}
