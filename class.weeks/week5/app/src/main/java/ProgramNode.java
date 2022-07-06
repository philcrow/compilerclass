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

    /**
     * This is not meaningful.
     */
    @Override
    public Integer getIntValue() {
        return 0;
    }

    @Override
    public boolean getBooleanValue() {
        return true;
    }

    public String toString() {
        return "program";
    }
}
