import java.util.List;

public class FunctionNode extends Node {
    List<Parameter> parameters;
    String returnType;
    Node block;

    public FunctionNode(List<Parameter> parameters, String returnType, Node block) {
        this.parameters = parameters;
        this.returnType = returnType;
        this.block = block;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public void act() { }

    public void invoke() {
        block.act();
    }

    @Override
    public boolean canBeInt() {
        return returnType.equals("int");
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
