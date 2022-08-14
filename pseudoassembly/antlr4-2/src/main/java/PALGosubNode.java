public class PALGosubNode extends PALNode {
    private PALOperand destination;
    private PALInterpretter interpretter;

    public PALGosubNode(String label, PALOperand destination, PALInterpretter interpretter) {
        super(label);
        this.destination = destination;
        this.interpretter = interpretter;
    }

    public void act() {
        interpretter.pushCallStack();
        interpretter.setProgramPointer(destination.resolveDestination());
    }

    public String toString() {
        return getLabelAsString() + "gosub " + destination.toString();
    }
}
