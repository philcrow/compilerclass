import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class EvalVisitor extends ExprBaseVisitor<Node> {
    SymbolTable symbols = SymbolTable.generateGlobalSymbolTable();

    // Helpers

    public Symbol resolve(String name) {
        return symbols.resolve(name);
    }

    public void setValue(String name, Integer newValue) {
        symbols.resolve(name).setIntValue(newValue);
    }

    public void setValue(String name, Double newValue) {
        symbols.resolve(name).setFloatValue(newValue);
    }

    public void setReturnValue(String name, Symbol symbol) {
        symbols.set(name, symbol);
    }

    public void pushCallStackFrame() {
        symbols = new SymbolTable(symbols);
    }

    public void popCallStackFrame() {
        symbols = symbols.getParent();
    }

    public void dumpSymbolTables() {
        System.err.println( "Symbols:\n" + symbols.toString());
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

        declare(type, id);
        return new AssignNode(id, valueNode, this);
    }

    /**
     * Puts a symbol into the table without value.
     * To give a value to symbol, call one of the setValue methods.
     */
    public Symbol declare(String type, String id) {
        Symbol newSymbol = Symbol.getInstance(type, id);
        symbols.set(id, newSymbol);
        return newSymbol;
    }

    @Override
    public Node visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        if (symbols.resolve(id) == null) {
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

    // this is a compile time only definition
    @Override
    public Node visitFunctionDefiniton(ExprParser.FunctionDefinitonContext ctx) {
        List<Parameter> parameters = collectParameters(ctx.paramList());
        Node block = visit(ctx.block());
        String returnType = ctx.type().getText();

        FunctionNode functionNode = new FunctionNode(parameters, returnType, block);

        String functionName = ctx.ID().getText();
        declare("function", functionName);
        Symbol functionSymbol = resolve(functionName);
        // we need to put the node into the symbol table, not just record its name
        functionSymbol.setFunctionValue(functionNode);

        return null;
    }

    private List<Parameter> collectParameters(ExprParser.ParamListContext ctx) {
        List<Parameter> answer = new ArrayList<>();
        while (ctx != null) {
            ExprParser.ParamContext paramCtx = ctx.param();
            String type = paramCtx.type().getText();
            String id = paramCtx.ID().getText();

            Parameter param = new Parameter(type, id);

            answer.add(param);
            ctx = ctx.paramList();
        }
        return answer;
    }

    @Override
    public Node visitCall(ExprParser.CallContext ctx) {
        String functionName = ctx.ID().getText();
        List<Node> arguments = collectArguments(ctx.exprList());
        // during run time we need to evaluate these based on the parameter types
        // of the function and load them into its symbol table

        return new CallNode(functionName, arguments, this);
    }

    private List<Node> collectArguments(ExprParser.ExprListContext ctx) {
        List<Node> answer = new ArrayList<>();
        while (ctx != null) {
            Node param = visit(ctx.expr());
            answer.add(param);
            ctx = ctx.exprList();
        }
        return answer;
    }

    @Override
    public Node visitReturn(ExprParser.ReturnContext ctx) {
        Node value = visit(ctx.expr());

        return new ReturnNode(value, this);
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
    public Node visitUnaryNegation(ExprParser.UnaryNegationContext ctx) {
        Node pleaseNegate = visit(ctx.expr());
        return new UnaryNegationNode(pleaseNegate);
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
