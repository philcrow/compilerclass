import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class PALRunner {
    public static void run(String fileName) throws Exception {
        CharStream input  = CharStreams.fromFileName(fileName);
        run(input);
    }

    public static PALInterpretter run(CharStream input) throws Exception {
        PALLexer          lexer  = new PALLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PALParser         parser = new PALParser(tokens);
        ParseTree         tree   = parser.program();
        ParseTreeWalker   walker = new ParseTreeWalker();

        PALInterpretter interpretter = new PALInterpretter();
        walker.walk(interpretter, tree);

        interpretter.run();

        return interpretter;
    }

    public static void main(String[] args) throws Exception {
        PALRunner.run(args[0]);
    }
}
