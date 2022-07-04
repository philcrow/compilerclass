public abstract class Node {
    /**
     * For statements to perform their behavior.
     */
    public abstract void act();

    /**
     * For expr nodes to compute their value, operations will recursively ask
     * children for values and combine them.
     * @return the value of an extpression
     */
    public abstract Integer getIntValue();
}
