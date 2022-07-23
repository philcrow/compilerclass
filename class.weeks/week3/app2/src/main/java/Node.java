public abstract class Node {
    /**
     * For statements to perform their behavior.
     */
    public abstract void act();

    /**
     * For statments to emit their assembly code.
     * @param register parents give this to their children so the children
     * can know where to place their final answer, parents free this register.
     * Top level ProgramNode doesn't need this and will receive null.
     * @return the assembly for the node
     */
    public abstract String emit(String register);

    /**
     * For expr nodes to compute their value, operations will recursively ask
     * children for values and combine them.
     * @return the value of an extpression
     */
    public abstract Integer getIntValue();
}
