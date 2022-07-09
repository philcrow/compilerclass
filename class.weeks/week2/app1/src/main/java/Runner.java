import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Runner {

    public int run(String fileName) throws Exception {
        CharStream input  = CharStreams.fromFileName(fileName);
        return run(input);
    }

    public int run(CharStream input) {
        CalcLexer         lexer  = new CalcLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcParser        parser = new CalcParser(tokens);
        ParseTree         tree   = parser.program();
        ParseTreeWalker   walker = new ParseTreeWalker();

        // Our custom code
        Interpretter  interpretter = new Interpretter();
        walker.walk(interpretter, tree);

        return interpretter.getAnswer();
    }

    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        Runner runner = new Runner();
        int answer = runner.run(fileName);

        System.out.println( "answer: " + answer);
    }
}
