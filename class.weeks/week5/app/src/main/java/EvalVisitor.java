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
        return buildProgramNode(ctx.statement());
    }

    private Node buildProgramNode(List<ExprParser.StatementContext> parserStatements) {
        List<Node> programStatements = new ArrayList<>();
        for (ExprParser.StatementContext statement : parserStatements) {
            Node programStatement = visit(statement);
            if (programStatement != null) {
                programStatements.add(programStatement);
            }
        }
        return new ProgramNode(programStatements);
    }

    // Statements

    @Override
    public Node visitPrintExpr(ExprParser.PrintExprContext ctx) {
        Node value = visit(ctx.expr());

        return new PrintNode(value);
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
        System.err.println( "visitDeclareAssign " + type + " " + id + " " + valueNode);

        declare(type, id);
        return new AssignNode(id, valueNode, this);
    }

    private void declare(String type, String id) {
        Symbol newSymbol = Symbol.getInstance(type, id);
        globalSymbols.set(id, newSymbol);
    }

    @Override
    public Node visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        if (globalSymbols.resolve(id) == null) {
            throw new RuntimeException("ID " + id + " must be declared");
        }
        Node valueNode = visit(ctx.expr());

        return new AssignNode(id, valueNode, this);
    }

    @Override
    public Node visitIfStatement(ExprParser.IfStatementContext ctx) {
        Node conditional = visit(ctx.conditional());
        Node thenBlock = visit(ctx.block());
        Node elseBlock = null;

        if (ctx.else_() != null) {
            elseBlock = visit(ctx.else_());
        }

        return new IfNode(conditional, thenBlock, elseBlock);
    }

    @Override
    public Node visitWhileStatement(ExprParser.WhileStatementContext ctx) {
        Node conditional = visit(ctx.conditional());
        Node block = visit(ctx.block());

        return new WhileNode(conditional, block);
    }

    // Primatives

    @Override
    public Node visitInt(ExprParser.IntContext ctx) {
        Integer value = Integer.parseInt(ctx.INT().getText());
        return new IntNode(value);
    }

    @Override
    public Node visitFloat(ExprParser.FloatContext ctx) {
        Double value = Double.parseDouble(ctx.FLOAT().getText());
        return new FloatNode(value);
    }

    @Override
    public Node visitId(ExprParser.IdContext ctx) {
        // statements will not traverse to this method, only exprs will do that
        String id = ctx.ID().getText();
        return new IdNode(id, this);
    }

    // Operations

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

    @Override
    public Node visitConditional(ExprParser.ConditionalContext ctx) {
        String comparitor = ctx.compare.getText();
        Node left = visit(ctx.expr(0));
        Node right = visit(ctx.expr(1));

        return new ConditionalNode(left, comparitor, right);
    }

    @Override
    public Node visitBlock(ExprParser.BlockContext ctx) {
        return buildProgramNode(ctx.statement());
    }

    @Override
    public Node visitElse(ExprParser.ElseContext ctx) {
        return visit(ctx.block());
    }
}
