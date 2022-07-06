public class FloatSymbol extends Symbol {
    Double value;

    public FloatSymbol(String name, Double initialValue) {
        super(name);
        value = initialValue;
    }

    public Integer getIntValue() {
        throw new RuntimeException("Symbol '" + getName() + "' is a float not an int");
    }

    public Double getFloatValue() {
        return value;
    }

    public void setIntValue(Integer newValue) {
        value = Double.valueOf(newValue);
    }

    public void setFloatValue(Double newValue) {
        value = newValue;
    }

    public String toString() {
        return "float " + getName() + " " + getFloatValue();
    }
}
