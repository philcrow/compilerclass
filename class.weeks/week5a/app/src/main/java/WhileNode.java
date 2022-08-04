public class WhileNode extends Node {
    Node conditional;
    Node block;

    public WhileNode(Node conditional, Node block) {
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
    public Integer getIntValue() {
        return conditional.getIntValue();
    }

    @Override
    public boolean getBooleanValue() {
        return conditional.getBooleanValue();
    }

    public String toString() {
        return "while (" + conditional + ")" + block;
    }
}
