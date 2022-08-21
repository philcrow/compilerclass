import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class AssemblyVisitor extends ExprBaseVisitor<Node> implements EvalVisitor<Node> {
    Map<String, Integer> symbolTable;
    Stack<String> freeRegisters;

    public AssemblyVisitor() {
        symbolTable = new HashMap<>();
        freeRegisters = initRegisters();
    }

    public String emit(Node program) {
        StringBuilder sb = new StringBuilder(".data\n");
        sb.append(".PRTSTR: .string \"%d\\n\"\n");
        String programEmission = program.emit(null);

        sb.append(emitSymbolTable());

        sb.append(".text\n");
        sb.append(".global main\n");
        sb.append("main:\n");
        sb.append(programEmission);

        return sb.toString();
    }

    private String emitSymbolTable() {
        StringBuilder sb = new StringBuilder();
        for (String symbol : symbolTable.keySet()) {
            sb.append(symbol + ": .quad " + symbolTable.get(symbol) + "\n");
        }
        return sb.toString();
    }

    // Helpers

    public Integer resolve(String symbol) {
        return symbolTable.get(symbol);
    }

    public void set(String symbol, Integer newValue) {
        symbolTable.put(symbol, newValue);
    }

    private Stack<String> initRegisters() {
        Stack<String> answer = new Stack<>();
        String[] names = new String[] {
            "%edx",
            "%ecx",
            "%ebx",
            "%eax",
        };
        Arrays.stream(names).forEach(name -> answer.push(name));

        return answer;
    }

    public String getFreeRegister() {
        if (freeRegisters.empty()) {
            throw new RuntimeException("No available registers.");
        }
        return freeRegisters.pop();
    }

    public void freeRegister(String register) {
        freeRegisters.push(register);
    }

    // Parse Tree Visitors

    @Override
    public Node visitProgram(ExprParser.ProgramContext ctx) {
        List<Node> statements = new ArrayList<>();
        for (ExprParser.StatementContext statement : ctx.statement()) {
            statements.add(visit(statement));
        }
        return new ProgramNode(statements);
    }

    @Override
    public Node visitPrintExpr(ExprParser.PrintExprContext ctx) {
        Node value = visit(ctx.expr());

        return new PrintNode(value, this);
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
            return new MultiplyNode(left, right, this);
        }
        else {
            return new DivideNode(left, right, this);
        }
    }

    @Override
    public Node visitAddSub(ExprParser.AddSubContext ctx) {
        Node left = visit(ctx.expr(0));
        Node right = visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.ADD) {
            return new AddNode(left, right, this);
        }
        else {
            return new SubtractNode(left, right, this);
        }
    }

    @Override
    public Node visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
