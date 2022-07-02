public class PALPromptNode extends PALNode {
    private String prompt;

    public PALPromptNode(String label, String prompt) {
        super(label);
        this.prompt = prompt.replace("\"", "");
    }

    public void act() {
        System.out.println( prompt );
    }

    public String toString() {
        return getLabelAsString() + "prompt " + prompt;
    }
}
