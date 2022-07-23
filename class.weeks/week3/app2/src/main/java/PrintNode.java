public class PrintNode extends Node {
    Node valueNode;
    EvalVisitor treeHelper;

    public PrintNode(Node valueNode, EvalVisitor treeHelper) {
        this.valueNode = valueNode;
        this.treeHelper = treeHelper;
    }

    @Override
    public void act() {
        Integer newValue = getIntValue();
        System.out.println(newValue);
    }

    @Override
    public String emit(String ignoredRegister) {
        String register = treeHelper.getFreeRegister();

        String childEmission = valueNode.emit(register);

        String myEmission = "movl " + register + ", %esi\n" +
            "leaq .PRTSTR(%rip), %rdi\n" +
            "movl $0, " + register + "\n" +
            "call printf@PLT\n";

        treeHelper.freeRegister(register);

        return childEmission + myEmission;
    }

    @Override
    public Integer getIntValue() {
        return valueNode.getIntValue();
    }

    public String toString() {
        return "print" + " [" + valueNode + "]";
    }
}
