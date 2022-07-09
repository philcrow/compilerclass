import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

// Visitor approach
public class Runner {
    public Integer run(String fileName) throws Exception {
        CharStream input  = CharStreams.fromFileName(fileName);
        return run(input);
    }

    public Integer run(CharStream input) {
        ExprLexer         lexer  = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser        parser = new ExprParser(tokens);
        ParseTree         tree   = parser.program();

        EvalVisitor eval = new EvalVisitor();
        return eval.visit(tree);
    }

    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        Runner runner = new Runner();
        Integer answer = runner.run(fileName);
    }
}
