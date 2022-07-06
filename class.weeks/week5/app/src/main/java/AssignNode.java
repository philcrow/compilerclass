public class AssignNode extends Node {
    String name;
    Node valueNode;
    EvalVisitor visitor;

    public AssignNode(String name, Node valueNode, EvalVisitor visitor) {
        this.name = name;
        this.valueNode = valueNode;
        this.visitor = visitor;
    }

    @Override
    public void act() {
        Integer newValue = getIntValue();
        visitor.setValue(name, newValue);
    }

    @Override
    public Integer getIntValue() {
        return valueNode.getIntValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "assign " + name + " " + valueNode;
    }
}
