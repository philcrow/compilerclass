import java.util.HashMap;
import java.util.Map;

public class DcEmitter extends ExprBaseVisitor<String> {
    // symbol table
    Map<String, String> symbolTable = new HashMap<>();

    @Override
    public String visitPrintExpr(ExprParser.PrintExprContext ctx) {
        String value = visit(ctx.expr());
        return value + "p\n";
    }

    @Override
    public String visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText(); // get the variable name
        String value = visit(ctx.expr()).trim();  // walk the expression subtree for value

        symbolTable.put(id, value);     // store the variable in the symbol table
        return "";
    }

    @Override
    public String visitInt(ExprParser.IntContext ctx) {
        return ctx.INT().getText() + "\n";
    }

    @Override
    public String visitId(ExprParser.IdContext ctx) {
        String id = ctx.ID().getText();

        if (symbolTable.containsKey(id)) {
            return symbolTable.get(id) + "\n";
        }
        else {
            System.err.println( "undefined symbol: " + id + " using 0");
            return "";
        }
    }

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
            return left + right + "+\n";
        }
        else {
            return left + right + "-\n";
        }
    }

    @Override
    public String visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
