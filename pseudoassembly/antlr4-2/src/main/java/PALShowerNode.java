public class PALShowerNode extends PALNode {
    private PALInterpretter interpretter;

    public PALShowerNode(String label, PALInterpretter interpretter) {
        super(label);
        this.interpretter = interpretter;
    }

    public void act() {
        interpretter.shower();
    }

    public String toString() {
        return getLabelAsString() + "shower";
    }
}
