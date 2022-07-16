import java.util.List;

public class FunctionNode extends Node {
    List<ParameterNode> parameters;
    String returnType;
    Node block;
    SymbolTable functionSymbols;

    public FunctionNode(List<ParameterNode> parameters, String returnType, Node block) {
        this.parameters = parameters;
        this.returnType = returnType;
        this.block = block;

        functionSymbols = new SymbolTable();
        for (ParameterNode parameter : parameters) {
            Symbol symbol = parameter.getSymbol();
            functionSymbols.set(symbol.getName(), symbol);
        }
    }

    @Override
    public void act() {
    }

    @Override
    public boolean canBeInt() {
        return returnType.equals("int");
    }

    @Override
    public Integer getIntValue() {
        try {
            block.act();
        }
        catch (ReturnEncounteredException ree) {
            Symbol returnValue = functionSymbols.resolve(ReturnNode.RETURN_SYMBOL);
            return returnValue.getIntValue();
        }
        return null;
    }

    @Override
    public Double getFloatValue() {
        try {
            block.act();
        }
        catch (ReturnEncounteredException ree) {
            Symbol returnValue = functionSymbols.resolve(ReturnNode.RETURN_SYMBOL);
            return returnValue.getFloatValue();
        }
        return null;
    }

    @Override
    public boolean getBooleanValue() {
        return false;
    }

    public List<ParameterNode> getParameters() {
        return parameters;
    }

    public SymbolTable getSymbolTable() {
        return functionSymbols;
    }

    public String toString() {
        return "function (" + parameters + ")" + block;
    }
}
