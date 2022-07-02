/**
 * Superclass for all PAL commands, implementation for blank lines.
 */
public class PALNode {
    private String label;

    /**
     * @param label the original line label, null if there wasn't one
     */
    public PALNode(String label) {
        this.label = label;
    }

    public void act() {}

    /**
     * Gives back what you gave the constructor which will be null if
     * the line had no label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gives back the label string.
     * @return either a blank string or
     * the label followed by a colon and one space
     */
    public String getLabelAsString() {
        if (label == null) {
            return "";
        }
        else {
            return label + ": ";
        }
    }

    public String toString() {
        return "";
    }
}
