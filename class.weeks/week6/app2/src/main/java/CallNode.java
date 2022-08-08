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
    public void act() { }

    private Symbol callFunction() {
        FunctionNode function = getFunctionNode();
        interpretter.pushCallStackFrame();

        passParameters(function);

        try {
            function.invoke();
        }
        catch (ReturnEncounteredException e) {
            // expected, happens whenever the function hits a return statement
        }

        Symbol returnValue = interpretter.resolve(ReturnNode.RETURN_SYMBOL);

        interpretter.popCallStackFrame();

        return returnValue;
    }

    private FunctionNode getFunctionNode() {
        Symbol functionSymbol = interpretter.resolve(functionName);
        return functionSymbol.getFunctionValue();
    }

    private void passParameters(FunctionNode function) {
        List<Parameter> parameters = function.getParameters();

        // TODO verification of count match (correct number of args)
        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = parameters.get(i);
            Node valueNode = arguments.get(i);

            // verify that the type on the parameter can be delivered by the valueNode
            if ("int".equals(parameter.getTypeName())) {
                if (! valueNode.canBeInt()) {
                    throw new RuntimeException("parameter " + parameter.getName() + " to function " +
                            functionName + " must be int");
                }

                interpretter.declare("int", parameter.getName());
                interpretter.setValue(parameter.getName(), valueNode.getIntValue());
            }
            else {
                interpretter.declare("float", parameter.getName());
                interpretter.setValue(parameter.getName(), valueNode.getFloatValue());
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
        if (!canBeInt()) {
            throw new RuntimeException("Float function " + functionName + " asked to give an int");
        }

        Symbol returnValue = callFunction();
        return returnValue.getIntValue();
    }

    @Override
    public Double getFloatValue() {
        Symbol returnValue = callFunction();
        return returnValue.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return false;
    }

    public String toString() {
        return "call " + functionName + "(" + arguments + ")";
    }
}
