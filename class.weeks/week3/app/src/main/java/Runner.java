import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

// Visitor approach
public class Runner {
    public static void main(String[] args) throws Exception {
        // As before with only name changes.
        CharStream        input  = CharStreams.fromFileName(args[0]);
        ExprLexer         lexer  = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser        parser = new ExprParser(tokens);
        ParseTree         tree   = parser.program();

        // Our custom code
        EvalVisitor eval = new EvalVisitor();
        Node program = eval.visit(tree);
        program.act();
    }
}
