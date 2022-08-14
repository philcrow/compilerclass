public class PALAllocNode extends PALNode {
    int size;

    public PALAllocNode(String label, int size) {
        super(label);
        this.size = size;
    }

    public void act() {
        // nothing to do here, allocation is a directive done during assembly
    }

    public String toString() {
        return getLabelAsString() + "alloc " + size;
    }
}
