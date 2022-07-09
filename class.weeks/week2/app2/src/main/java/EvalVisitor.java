import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends ExprBaseVisitor<Integer> {
    // symbol table
    Map<String, Integer> symbolTable = new HashMap<>();

    @Override
    public Integer visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText(); // get the variable name
        int value = visit(ctx.expr());  // walk the expression subtree for value

        symbolTable.put(id, value);     // store the variable in the symbol table
        return value;
    }

    @Override
    public Integer visitPrintExpr(ExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }

    @Override
    public Integer visitInt(ExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(ExprParser.IdContext ctx) {
        String id = ctx.ID().getText();

        if (symbolTable.containsKey(id)) {
            return symbolTable.get(id);
        }
        else {
            System.err.println( "undefined symbol: " + id + " using 0");
            return 0;
        }
    }

    @Override
    public Integer visitMulDiv(ExprParser.MulDivContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.MUL) {
            return left * right;
        }
        else {
            return left / right;
        }
    }

    @Override
    public Integer visitAddSub(ExprParser.AddSubContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.ADD) {
            return left + right;
        }
        else {
            return left - right;
        }
    }

    @Override
    public Integer visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
