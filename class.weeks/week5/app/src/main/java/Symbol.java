public abstract class Symbol {
    String name;
    SymbolType type;

    public Symbol(String name, SymbolType type) {
        this.name = name;
        this.type = type;
    }

    public static Symbol getInstance(String typeName, String symbolName) {
        Symbol answer = null;

        switch (typeName) {
            case "int" :
                return new IntSymbol(symbolName);
            case "float" :
                return new FloatSymbol(symbolName);
        }

        return answer; // unreachable, the grammar will insist on a valid choice
    }

    public abstract Integer getIntValue();
    public abstract Double getFloatValue();
    public abstract void setIntValue(Integer newValue);
    public abstract void setFloatValue(Double newValue);

    public String getName() {
        return name;
    }

    public SymbolType getType() {
        return type;
    }

    public enum SymbolType {
        INT,
        FLOAT
    }
}
