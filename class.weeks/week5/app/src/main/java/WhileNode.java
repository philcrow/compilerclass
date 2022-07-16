public class WhileNode extends Node {
    Node conditional;
    Node block;

    public WhileNode(int lineNumber, Node conditional, Node block) {
        super(lineNumber);
        this.conditional = conditional;
        this.block = block;
    }

    @Override
    public void act() {
        while (getBooleanValue()) {
            block.act();
        }
    }

    @Override
    public boolean canBeInt() {
        return conditional.canBeInt();
    }

    @Override
    public Integer getIntValue() {
        return conditional.getIntValue();
    }

    @Override
    public Double getFloatValue() {
        return conditional.getFloatValue();
    }

    @Override
    public boolean getBooleanValue() {
        return conditional.getBooleanValue();
    }

    public String toString() {
        return "while (" + conditional + ")" + block;
    }
}
