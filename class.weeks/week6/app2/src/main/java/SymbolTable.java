import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    Map<String, Symbol> table;
    SymbolTable parent;

    /**
     * Makes a new symbol table. There are two times to do this.
     * First, at the outset of compilation for global symbols.
     * Second, is upon each function call.
     * @param parent null for the global symbol table, or the
     * table in scope for the caller of a function
     */
    public SymbolTable(SymbolTable parent) {
        table = new HashMap<String, Symbol>();
        this.parent = parent;
    }

    /**
     * For the top level compiler to call to create the global symbol table.
     */
    public static SymbolTable generateGlobalSymbolTable() {
        return new SymbolTable(null);
    }

    /**
     * CallNodes who created an instance to service function
     * should call this and report it back to the EvalVisitor.
     */
    public SymbolTable getParent() {
        return parent;
    }

    /**
     * @return the Symbol for name sought first in the current
     * function's table, and then up through all of its parents
     * @throws RuntimeException when name is not in any of the chained tables
     */
    public Symbol resolve(String name) {
        if (table.containsKey(name)) {
            return table.get(name);
        }
        else if (parent != null) {
            return parent.resolve(name);
        }
        else {
            throw new RuntimeException("No such symbol " + name);
        }
    }

    /**
     * Always puts the symbol into the table at the inner most scope.
     */ 
    public void set(String name, Symbol symbol) {
        table.put(name, symbol);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SymbolTable l = this; l != null; l = l.parent) {
            sb.append(l.table + "\n");
        }
        return sb.toString();
    }
}
