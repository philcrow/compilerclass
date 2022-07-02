import java.util.Scanner;

public class PALTakeNode extends PALNode {
    private PALOperand op;
    private PALInterpretter interpretter;
    public static Scanner keyboard;

    static {
        keyboard = new Scanner(System.in);
    }

    public PALTakeNode(String label, PALOperand op, PALInterpretter interpretter) {
        super(label);
        this.op = op;
        this.interpretter = interpretter;
    }

    public void act() {
        Double value = keyboard.nextDouble();
        interpretter.storeValue(op, value);
    }

    public String toString() {
        return getLabelAsString() + "take " + op.toString();
    }
}
