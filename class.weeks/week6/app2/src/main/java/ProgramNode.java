import java.util.List;

public class ProgramNode extends Node {
    List<Node> statements;

    public ProgramNode(List<Node> statements) {
        this.statements = statements;
    }

    @Override
    public void act() {
        for (Node statement : statements) {
            statement.act();
        }
    }

    /*
     * The rest of the methods are not meaningful.
     */

    @Override
    public boolean canBeInt() {
        return true;
    }

    @Override
    public Integer getIntValue() {
        return 0;
    }

    @Override
    public Double getFloatValue() {
        return 0.0;
    }

    @Override
    public boolean getBooleanValue() {
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node statement : statements) {
            sb.append("[" + statement.toString() + "]\n");
        }
        return sb.toString();
    }
}
