public class PALEndNode extends PALNode {
    private Integer exitStatus;

    public PALEndNode(String label, Integer exitStatus) {
        super(label);
        this.exitStatus = exitStatus;
    }

    public void act() {
        System.exit(exitStatus);
    }

    public String toString() {
        return getLabelAsString() + "end " + exitStatus;
    }
}
