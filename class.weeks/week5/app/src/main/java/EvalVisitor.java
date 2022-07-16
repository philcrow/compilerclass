import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends ExprBaseVisitor<Node> {
    SymbolTable globalSymbols;

    public EvalVisitor() {
        globalSymbols = new SymbolTable();
    }

    // Helpers

    public Symbol resolve(String name) {
        return globalSymbols.resolve(name);
    }

    public void setValue(String name, Integer newValue) {
        globalSymbols.resolve(name).setIntValue(newValue);
    }

    public void setValue(String name, Double newValue) {
        globalSymbols.resolve(name).setFloatValue(newValue);
    }

    // Parse Tree Visitors

    @Override
    public Node visitProgram(ExprParser.ProgramContext ctx) {
        int lineNumber = ctx.getStart().getLine();
        return buildProgramNode(lineNumber, ctx.statement());
    }

    private Node buildProgramNode(int lineNumber, List<ExprParser.StatementContext> parserStatements) {
        List<Node> programStatements = new ArrayList<>();
        for (ExprParser.StatementContext statement : parserStatements) {
            Node programStatement = visit(statement);
            if (programStatement != null) {
                programStatements.add(programStatement);
            }
        }
        return new ProgramNode(lineNumber, programStatements);
    }

    // Statements

    @Override
    public Node visitPrintExpr(ExprParser.PrintExprContext ctx) {
        Node value = visit(ctx.expr());

        int lineNumber = ctx.getStart().getLine();
        return new PrintNode(lineNumber, value);
    }

    // this is a compile time only statement
    @Override
    public Node visitDeclare(ExprParser.DeclareContext ctx) {
        String type = ctx.type().getText();
        String id = ctx.ID().getText();

        declare(type, id);
        return null;
    }

    @Override
    public Node visitDeclareAssign(ExprParser.DeclareAssignContext ctx) {
        String type = ctx.type().getText();
        String id = ctx.ID().getText();
        Node valueNode = visit(ctx.expr());
        int lineNumber = ctx.getStart().getLine();

        declare(type, id);
        return new AssignNode(lineNumber, id, valueNode, this);
    }

    private void declare(String type, String id) {
        Symbol newSymbol = Symbol.getInstance(type, id);
        globalSymbols.set(id, newSymbol);
    }

    @Override
    public Node visitAssign(ExprParser.AssignContext ctx) {
        int lineNumber = ctx.getStart().getLine();
        String id = ctx.ID().getText();
        if (globalSymbols.resolve(id) == null) {
            throw new RuntimeException("ID " + id + " must be declared at line " + lineNumber);
        }
        Node valueNode = visit(ctx.expr());

        return new AssignNode(lineNumber, id, valueNode, this);
    }

    @Override
    public Node visitIfStatement(ExprParser.IfStatementContext ctx) {
        Node conditional = visit(ctx.conditional());
        Node thenBlock = visit(ctx.block());
        Node elseBlock = null;

        if (ctx.else_() != null) {
            elseBlock = visit(ctx.else_());
        }

        int lineNumber = ctx.getStart().getLine();
        return new IfNode(lineNumber, conditional, thenBlock, elseBlock);
    }

    @Override
    public Node visitWhileStatement(ExprParser.WhileStatementContext ctx) {
        Node conditional = visit(ctx.conditional());
        Node block = visit(ctx.block());

        int lineNumber = ctx.getStart().getLine();
        return new WhileNode(lineNumber, conditional, block);
    }

    // Primatives

    @Override
    public Node visitInt(ExprParser.IntContext ctx) {
        Integer value = Integer.parseInt(ctx.INT().getText());
        int lineNumber = ctx.getStart().getLine();
        return new IntNode(lineNumber, value);
    }

    @Override
    public Node visitFloat(ExprParser.FloatContext ctx) {
        Double value = Double.parseDouble(ctx.FLOAT().getText());
        int lineNumber = ctx.getStart().getLine();
        return new FloatNode(lineNumber, value);
    }

    @Override
    public Node visitId(ExprParser.IdContext ctx) {
        // statements will not traverse to this method, only exprs will do that
        String id = ctx.ID().getText();
        int lineNumber = ctx.getStart().getLine();
        return new IdNode(lineNumber, id, this);
    }

    // Operations

    @Override
    public Node visitMulDiv(ExprParser.MulDivContext ctx) {
        Node left = visit(ctx.expr(0));
        Node right = visit(ctx.expr(1));

        int lineNumber = ctx.getStart().getLine();
        if (ctx.op.getType() == ExprParser.MUL) {
            return new MultiplyNode(lineNumber, left, right);
        }
        else {
            return new DivideNode(lineNumber, left, right);
        }
    }

    @Override
    public Node visitAddSub(ExprParser.AddSubContext ctx) {
        Node left = visit(ctx.expr(0));
        Node right = visit(ctx.expr(1));

        int lineNumber = ctx.getStart().getLine();
        if (ctx.op.getType() == ExprParser.ADD) {
            return new AddNode(lineNumber, left, right);
        }
        else {
            return new SubtractNode(lineNumber, left, right);
        }
    }

    @Override
    public Node visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Node visitConditional(ExprParser.ConditionalContext ctx) {
        String comparitor = ctx.compare.getText();
        Node left = visit(ctx.expr(0));
        Node right = visit(ctx.expr(1));

        int lineNumber = ctx.getStart().getLine();
        return new ConditionalNode(lineNumber, left, comparitor, right);
    }

    @Override
    public Node visitBlock(ExprParser.BlockContext ctx) {
        int lineNumber = ctx.getStart().getLine();
        return buildProgramNode(lineNumber, ctx.statement());
    }

    @Override
    public Node visitElse(ExprParser.ElseContext ctx) {
        return visit(ctx.block());
    }
}
