public class SubtractNode extends Node {
    Node left;
    Node right;
    EvalVisitor treeHelper;

    public SubtractNode(Node left, Node right, EvalVisitor treeHelper) {
        this.left = left;
        this.right = right;
        this.treeHelper = treeHelper;
    }

    @Override
    public void act() {
    }

    @Override
    public String emit(String register) {
        String leftRegister = treeHelper.getFreeRegister();
        // right register is the one that was given to me
        String leftEmission = left.emit(leftRegister);
        String rightEmission = right.emit(register);

        String myEmission = "subt " + leftRegister + " " + register + "\n";

        treeHelper.freeRegister(leftRegister);

        return leftEmission + rightEmission + myEmission;
    }

    @Override
    public Integer getIntValue() {
        return left.getIntValue() - right.getIntValue();
    }

    public String toString() {
        return "subtract " + left + " - " + right;
    }
}
