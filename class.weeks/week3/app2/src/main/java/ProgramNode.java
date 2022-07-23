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
     * @ignoredRegister ignored, callers should pass null
     */
    @Override
    public String emit(String ignoredRegister) {
        StringBuilder sb = new StringBuilder();
        for (Node statement : statements) {
            sb.append(statement.emit(null));
        }
        return sb.toString();
    }

    /**
     * This is not meaningful.
     */
    @Override
    public Integer getIntValue() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node statement : statements) {
            sb.append("[" + statement.toString() + "]");
        }
        return sb.toString();
    }
}
