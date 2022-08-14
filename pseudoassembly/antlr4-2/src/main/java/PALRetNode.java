public class PALRetNode extends PALNode {
    private PALInterpretter interpretter;

    public PALRetNode(String label, PALInterpretter interpretter) {
        super(label);
        this.interpretter = interpretter;
    }

    public void act() {
        interpretter.popCallStack();
    }

    public String toString() {
        return getLabelAsString() + "ret";
    }
}
