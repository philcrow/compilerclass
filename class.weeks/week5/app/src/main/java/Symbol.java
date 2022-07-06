public abstract class Symbol {
    String name;

    public Symbol(String name) {
        this.name = name;
    }

    public abstract Integer getIntValue();
    public abstract Double getFloatValue();
    public abstract void setIntValue(Integer newValue);
    public abstract void setFloatValue(Double newValue);

    public String getName() {
        return name;
    }
}
