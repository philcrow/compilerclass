public class IdNode extends Node {
    String name;
    EvalVisitor visitor;

    public IdNode(String name, EvalVisitor visitor) {
        this.name = name;
        this.visitor = visitor;
    }

    @Override
    public void act() {
    }

    @Override
    public Integer getIntValue() {
        return visitor.resolve(name).getIntValue();
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }

    public String toString() {
        return "id " + name;
    }
}
