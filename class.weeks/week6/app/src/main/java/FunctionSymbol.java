public class FunctionSymbol extends Symbol {
    FunctionNode value;

    public FunctionSymbol(String name) {
        super(name, Symbol.SymbolType.FUNCTION);
    }

    public Integer getIntValue() {
        // We need to run the block and collect the return value.
        // Currently running involves calling act, here on the block inside the FunctionNode.
        // We need a return statment which has an expression.
        // That needs to be able to stop the block from executing.
        // We could throw a ReturnReachedException and trap that in the block.
        // Otherwise, act methods would have to return something, perhaps a boolean?
        // before throwing, the return would collect its expression based on the
        // desired return type and store it in a special location in the symbol table (an
        // illegal identifier like: 1returnValue).
        return null;
    }

    public Double getFloatValue() {
        return null;
    }

    public void setIntValue(Integer newValue) {

    }

    public void setFloatValue(Double newValue) {
    }

    public String toString() {
        return "function";
    }
}
