import java.util.List;

public class PALPrintNode extends PALNode {
    private List<PALOperand> ops;

    public PALPrintNode(
        String label,
        List<PALOperand> ops,
        PALInterpretter interpretter
    ) {
        super(label);
        this.ops = ops;
    }

    public void act() {
        StringBuilder sb = new StringBuilder();
        for (PALOperand op : ops) {
            Double value = op.retrieveValue();
            sb.append("" + value + " ");
        }
        System.out.println(sb.toString().trim());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PALOperand op : ops) {
            sb.append(op.toString() + " ");
        }
        return getLabelAsString() + "prt " + sb.toString().trim();
    }
}
