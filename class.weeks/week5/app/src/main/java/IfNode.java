public class IfNode extends Node {
    Node conditional;
    Node thenBlock;
    Node elseBlock;

    public IfNode(int lineNumber, Node conditional, Node thenBlock, Node elseBlock) {
        super(lineNumber);
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
    public boolean canBeInt() {
        return true;
    }

    @Override
    public Integer getIntValue() {
        return conditional.getIntValue();
    }

    @Override
    public Double getFloatValue() {
        return Double.valueOf(conditional.getIntValue());
    }

    @Override
    public boolean getBooleanValue() {
        return conditional.getBooleanValue();
    }

    public String toString() {
        if (elseBlock == null) {
            return "if (" + conditional + ")then\n" + thenBlock;
        }
        else {
            return "if (" + conditional + ")then\n" + thenBlock + "else\n" + elseBlock;
        }
    }
}
