import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    Map<String, Symbol> table;

    public SymbolTable() {
        table = new HashMap<String, Symbol>();
    }

    public Symbol resolve(String name) {
        return table.get(name);
    }

    public void set(String name, Symbol symbol) {
        table.put(name, symbol);
    }
}
