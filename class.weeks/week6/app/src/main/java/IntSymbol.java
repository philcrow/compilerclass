public class IntSymbol extends Symbol {
    Integer value;

    public IntSymbol(String name) {
        super(name, Symbol.SymbolType.INT);
    }

    public Integer getIntValue() {
        if (value == null) {
            throw new RuntimeException("Symbol '" + getName() + "' used before definition");
        }
        return value;
    }

    public Double getFloatValue() {
        return Double.valueOf(value);
    }

    public FunctionNode getFunctionValue() {
        throw new RuntimeException("Symbol '" + getName() + "' is an int which cannot return a float");
    }

    public void setIntValue(Integer newValue) {
        value = newValue;
    }

    public void setFloatValue(Double newValue) {
        throw new RuntimeException("Symbol '" + getName() + "' is an int which cannot hold a float");
    }

    public void setFunctionValue(FunctionNode illegal) {
        throw new RuntimeException("Symbol '" + getName() + "' is an int which cannot hold a function");
    }

    public String toString() {
        return "int " + getName() + " " + getIntValue();
    }
}
