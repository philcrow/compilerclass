import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

// Visitor approach
public class RunEmitter {
    public String run(String fileName) throws Exception {
        CharStream input  = CharStreams.fromFileName(fileName);
        return run(input);
    }

    public String run(CharStream input) {
        ExprLexer         lexer  = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser        parser = new ExprParser(tokens);
        ParseTree         tree   = parser.program();

        DcEmitter eval = new DcEmitter();
        return eval.visit(tree);
    }

    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        RunEmitter runner = new RunEmitter();
        String answer = runner.run(fileName);
        System.out.println(answer);
    }
}
