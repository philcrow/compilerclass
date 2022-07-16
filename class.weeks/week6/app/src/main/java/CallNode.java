import java.util.List;

public class CallNode extends Node {
    String functionName;
    List<Node> arguments;
    EvalVisitor interpretter;

    public CallNode(String functionName, List<Node> arguments, EvalVisitor interpretter) {
        this.functionName = functionName;
        this.arguments = arguments;
        this.interpretter = interpretter;
    }

    @Override
    public void act() {
    }

    private FunctionNode getFunctionNode() {
        Symbol functionSymbol = interpretter.resolve(functionName);
        return functionSymbol.getFunctionValue();
    }

    private void passParameters(FunctionNode function) {
        List<ParameterNode> parameters = function.getParameters();
        for (int i = 0; i < parameters.size(); i++) {
            ParameterNode parameter = parameters.get(i);
            Symbol symbol = parameter.getSymbol();

            if (symbol.getType() == Symbol.SymbolType.INT) {
                Integer argValue = arguments.get(i).getIntValue();
                symbol.setIntValue(argValue);
            }
            else if (symbol.getType() == Symbol.SymbolType.FLOAT) {
                Double argValue = arguments.get(i).getFloatValue();
                symbol.setFloatValue(argValue);
            }
            else {
                throw new RuntimeException("Only int and float can be passed to functions");
            }
        }
    }

    @Override
    public boolean canBeInt() {
        FunctionNode function = getFunctionNode();
        return function.canBeInt();
    }

    @Override
    public Integer getIntValue() {
        FunctionNode function = getFunctionNode();
        SymbolTable functionSymbols = function.getSymbolTable();
        interpretter.pushSymbolTable(functionSymbols);

        passParameters(function);
        Integer answer = function.getIntValue();

        interpretter.popSymbolTable();

        return answer;
    }

    @Override
    public Double getFloatValue() {
        FunctionNode function = getFunctionNode();
        SymbolTable functionSymbols = function.getSymbolTable();
        interpretter.pushSymbolTable(functionSymbols);

        passParameters(function);
        Double answer = function.getFloatValue();

        interpretter.popSymbolTable();

        return answer;
    }

    @Override
    public boolean getBooleanValue() {
        return false;
    }

    public String toString() {
        return "call " + functionName + "(" + arguments + ")";
    }
}
