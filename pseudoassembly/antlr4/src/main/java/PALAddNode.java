public class PALAddNode extends PALNode {
    private PALOperand op1;
    private PALOperand op2;
    private PALInterpretter interpretter;

    public PALAddNode(
            String label,
            PALOperand op1,
            PALOperand op2,
            PALInterpretter interpretter
    ) {
        super(label);
        this.op1 = op1;
        this.op2 = op2;
        this.interpretter = interpretter;
    }

    public void act() {
        Double source = op1.retrieveValue();
        Double dest = op2.retrieveValue();
        Double answer = dest + source;
        interpretter.storeValue(op2, answer);
    }

    public String toString() {
        return getLabelAsString() + "add " + op1.toString() + " " + op2.toString();
    }
}
