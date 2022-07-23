import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

// Visitor approach
public class Runner {
    public void run(String fileName) throws Exception {
        CharStream input = CharStreams.fromFileName(fileName);
        run(input);
    }

    public Node run(CharStream input) {
        ExprLexer         lexer  = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser        parser = new ExprParser(tokens);
        ParseTree         tree   = parser.program();

        EvalVisitor eval = new EvalVisitor();
        Node program = eval.visit(tree);
        program.act();

        return program;
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new Runner();
        runner.run(args[0]);
    }
}
