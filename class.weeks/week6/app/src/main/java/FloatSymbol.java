public class FloatSymbol extends Symbol {
    Double value;

    public FloatSymbol(String name) {
        super(name, Symbol.SymbolType.FLOAT);
    }

    public Integer getIntValue() {
        throw new RuntimeException("Symbol '" + getName() + "' is a float not an int");
    }

    public Double getFloatValue() {
        return value;
    }

    public FunctionNode getFunctionValue() {
        throw new RuntimeException("Symbol '" + getName() + "' is a float not a function");
    }

    public void setIntValue(Integer newValue) {
        value = Double.valueOf(newValue);
    }

    public void setFloatValue(Double newValue) {
        value = newValue;
    }

    public void setFunctionValue(FunctionNode newValue) {
        // throw?
    }

    public String toString() {
        return "float " + getName() + " " + getFloatValue();
    }
}
