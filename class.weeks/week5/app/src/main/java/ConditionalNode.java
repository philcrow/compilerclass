public class ConditionalNode extends Node {
    Node left;
    String comparitor;
    Node right;

    public ConditionalNode(Node left, String comparitor, Node right) {
        this.left = left;
        this.comparitor = comparitor;
        this.right = right;
    }

    @Override
    public void act() {
    }

    @Override
    public Integer getIntValue() {
        return getBooleanValue() ? 1 : 0;
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
