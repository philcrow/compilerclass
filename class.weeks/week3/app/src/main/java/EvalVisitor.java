import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends ExprBaseVisitor<Node> {
    ProgramNode program;
    Map<String, Integer> symbolTable;

    public EvalVisitor() {
        symbolTable = new HashMap<>();
    }

    // Helpers

    public Integer resolve(String symbol) {
        return symbolTable.get(symbol);
    }

    public void set(String symbol, Integer newValue) {
        symbolTable.put(symbol, newValue);
    }

    // Parse Tree Visitors

    @Override public Node visitProgram(ExprParser.ProgramContext ctx) {
        List<Node> statements = new ArrayList<>();
        for (ExprParser.StatementContext statement : ctx.statement()) {
            statements.add(visit(statement));
        }
        return new ProgramNode(statements);
    }

    @Override
    public Node visitPrintExpr(ExprParser.PrintExprContext ctx) {
        Node value = visit(ctx.expr());

        return new PrintNode(value);
    }

    @Override
    public Node visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Node valueNode = visit(ctx.expr());

        return new AssignNode(id, valueNode, this);
    }

    @Override
    public Node visitInt(ExprParser.IntContext ctx) {
        Integer value = Integer.parseInt(ctx.INT().getText());
        return new IntNode(value);
    }

    @Override
    public Node visitId(ExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        return new IdNode(id, this);
    }

    @Override
    public Node visitMulDiv(ExprParser.MulDivContext ctx) {
        Node left = visit(ctx.expr(0));
        Node right = visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.MUL) {
            return new MultiplyNode(left, right);
        }
        else {
            return new DivideNode(left, right);
        }
    }

    @Override
    public Node visitAddSub(ExprParser.AddSubContext ctx) {
        Node left = visit(ctx.expr(0));
        Node right = visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.ADD) {
            return new AddNode(left, right);
        }
        else {
            return new SubtractNode(left, right);
        }
    }

    @Override
    public Node visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
