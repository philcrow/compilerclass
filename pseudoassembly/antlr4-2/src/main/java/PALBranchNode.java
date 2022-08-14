public class PALBranchNode extends PALNode {
    private String operator;
    private PALOperand left;
    private PALOperand right;
    private PALOperand destination;
    private PALInterpretter interpretter;

    public PALBranchNode(
            String label,
            String operator,
            PALOperand left,
            PALOperand right,
            PALOperand destination,
            PALInterpretter interpretter
    ) {
        super(label);
        this.operator = operator;
        this.left = left;
        this.right = right;
        this.destination = destination;
        this.interpretter = interpretter;
    }

    public void act() {
        Double value1 = left.retrieveValue();
        Double value2 = right.retrieveValue();

        boolean makeTheJump = false;

        switch(operator) {
            case "gt" : makeTheJump = value1 >  value2; break;
            case "ge" : makeTheJump = value1 >= value2; break;
            case "lt" : makeTheJump = value1 <  value2; break;
            case "le" : makeTheJump = value1 <= value2; break;
            case "eq" : makeTheJump = value1 == value2; break;
            case "ne" : makeTheJump = value1 != value2; break;
        }

        if (makeTheJump) {
            interpretter.setProgramPointer(destination.resolveDestination());
        }
    }

    public String toString() {
        return getLabelAsString() + "br" + operator + " " + left.toString() + " " + right.toString() + " " + destination.toString();
    }
}
