public class IntSymbol extends Symbol {
    Integer value;

    public IntSymbol(String name) {
        super(name, Symbol.SymbolType.INT);
    }

    public Integer getIntValue() {
        if (value == null) {
            throw new RuntimeException("Symbol '" + getName() + "' has no value");
        }
        return value;
    }

    public Double getFloatValue() {
        return Double.valueOf(value);
    }

    public void setIntValue(Integer newValue) {
        value = newValue;
    }

    public void setFloatValue(Double newValue) {
        // this is prevented by AssignNode
        throw new RuntimeException("Symbol '" + getName() + "' is an int which cannot hold a float");
    }

    public String toString() {
        return "int " + getName() + " " + getIntValue();
    }
}
