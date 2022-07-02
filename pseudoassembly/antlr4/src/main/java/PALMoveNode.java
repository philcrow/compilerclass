public class PALMoveNode extends PALNode {
    private PALOperand source;
    private PALOperand destination;
    private PALInterpretter interpretter;

    public PALMoveNode(String label, PALOperand source, PALOperand destination, PALInterpretter interpretter) {
        super(label);
        this.source = source;
        this.destination = destination;
        this.interpretter = interpretter;
    }

    public void act() {
        Double value = source.retrieveValue();
        interpretter.storeValue(destination, value);
    }

    public String toString() {
        return getLabelAsString() + "move " + source.toString() + " " + destination.toString();
    }
}
