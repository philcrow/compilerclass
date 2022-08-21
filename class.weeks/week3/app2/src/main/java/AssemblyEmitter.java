// Abandoned, EvalVisitor implementations were modified instead.
// This was left over from a prior attempt at emitting done in week3/app.
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class AssemblyEmitter extends ExprBaseVisitor<String> {
    // symbol table
    Map<String, String> symbolTable = new HashMap<>();

    // free list of register names
    Stack<String> freeRegisters = new Stack<String>();

    // Global Declarations, Main

    @Override
    public String visitProgram(ExprParser.ProgramContext ctx) {
        initRegisters();
        StringBuilder sb = new StringBuilder(".data\n");
        sb.append(".PRTSTR: .string \"%d\\n\"\n");
        String childCode = visitChildren(ctx);
        for (String symbol : symbolTable.keySet()) {
            sb.append("." + symbol + ": .quad " + symbolTable.get(symbol) + "\n");
        }
        sb.append(".global main\n");
        sb.append("main:\n");
        sb.append(childCode);
        return sb.toString();
    }

    private void initRegisters() {
        String[] names = new String[] { "%r1", "%r2", "%r3" };
        Arrays.stream(names).forEach(name -> freeRegisters.push(name));
    }

    // Statements

    @Override
    public String visitPrintExpr(ExprParser.PrintExprContext ctx) {
        String value = visit(ctx.expr());
        return null;
    }

    @Override
    public String visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText(); // get the variable name
        String value = visit(ctx.expr()).trim();  // walk the expression subtree for value

        symbolTable.put(id, value);     // store the variable in the symbol table
        return "";
    }

    // Operations

    @Override
    public String visitMulDiv(ExprParser.MulDivContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.MUL) {
            return left + right + "*\n";
        }
        else {
            return left + right + "/\n";
        }
    }

    @Override
    public String visitAddSub(ExprParser.AddSubContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.ADD) {
            // get a register to use
            // put left into the register
            // use add op with right destination register
//            return left + right + "+\n";
        }
        else {
//            return left + right + "-\n";
        }
        return "";
    }

    // Primatives

    @Override
    public String visitInt(ExprParser.IntContext ctx) {
        return "$" + ctx.INT().getText();
    }

    @Override
    public String visitId(ExprParser.IdContext ctx) {
        String id = ctx.ID().getText();

        if (symbolTable.containsKey(id)) {
            return symbolTable.get(id);
        }
        else {
            System.err.println( "undefined symbol: " + id + " using 0");
            return "";
        }
    }

    @Override
    public String visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
