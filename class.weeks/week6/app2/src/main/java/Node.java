public abstract class Node {
    /**
     * For statements to perform their behavior.
     */
    public abstract void act();

    /**
     * Tells you whether you can use this node as an int.
     */
    public abstract boolean canBeInt();

    /**
     * For expr nodes to compute their value, operations will recursively ask
     * children for values and combine them.
     * @return the value of an extpression
     */
    public abstract Integer getIntValue();

    /**
     * For expr nodes to compute with floating point numbers.
     */
    public abstract Double getFloatValue();

    /**
     * For conditionals.
     */
    public abstract boolean getBooleanValue();
}
