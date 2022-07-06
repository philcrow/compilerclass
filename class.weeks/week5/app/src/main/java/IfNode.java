public class IfNode extends Node {
    Node conditional;
    Node thenBlock;
    Node elseBlock;

    public IfNode(Node conditional, Node thenBlock, Node elseBlock) {
        this.conditional = conditional;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public void act() {
        if (getBooleanValue()) {
            thenBlock.act();
        }
        else if (elseBlock != null) {
            elseBlock.act();
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
        return "if (" + conditional + ")" + thenBlock + " " + elseBlock;
    }
}
