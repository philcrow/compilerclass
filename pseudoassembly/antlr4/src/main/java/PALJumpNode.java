public class PALJumpNode extends PALNode {
    private PALOperand destination;
    private PALInterpretter interpretter;

    public PALJumpNode(String label, PALOperand destination, PALInterpretter interpretter) {
        super(label);
        this.destination = destination;
        this.interpretter = interpretter;
    }

    public void act() {
        interpretter.setProgramPointer(destination.resolveDestination());
    }

    public String toString() {
        return getLabelAsString() + "jump " + destination.toString();
    }
}
