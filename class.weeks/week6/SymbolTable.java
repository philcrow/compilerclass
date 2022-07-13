import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    Map<String, Symbol> table;
    SymbolTable parent;

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
        table = new HashMap<String, Symbol>();
    }

    public Symbol resolve(String name) {
        Symbol answer = table.get(name);

        if (answer == null) {
            answer = parent.resolve(name);
        }

        return anwer;
    }
}
