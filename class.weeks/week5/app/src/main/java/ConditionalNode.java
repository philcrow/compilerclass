public class ConditionalNode extends Node {
    Node left;
    String comparitor;
    Node right;

    public ConditionalNode(int lineNumber, Node left, String comparitor, Node right) {
        super(lineNumber);
        this.left = left;
        this.comparitor = comparitor;
        this.right = right;
    }

    @Override
    public void act() { }

    @Override
    public boolean canBeInt() {
        return true;
    }

    @Override
    public Integer getIntValue() {
        return getBooleanValue() ? 1 : 0;
    }

    @Override
    public Double getFloatValue() {
        return Double.valueOf(getIntValue());
    }

    @Override
    public boolean getBooleanValue() {
        Integer leftValue = left.getIntValue();
        Integer rightValue = right.getIntValue();

        switch (comparitor) {
            case "==" :
                return leftValue == rightValue;
            case "!=" :
                return leftValue != rightValue;
            case "<" :
                return leftValue < rightValue;
            case ">" :
                return leftValue > rightValue;
            case "<=" :
                return leftValue <= rightValue;
            case ">=" :
                return leftValue >= rightValue;
        }
        return false; // unreachable, parser would have caught illegal comparitor
    }

    public String toString() {
        return "compare " + left + " " + comparitor + " " + right;
    }
}
