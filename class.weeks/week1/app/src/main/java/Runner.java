import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Runner {
    public int run(String fileName) throws Exception {
        // 1. get the input into a stream from a file passed on command line
        CharStream input  = CharStreams.fromFileName(fileName);
        return run(input);
    }

    public int run(CharStream input) {
        // there are other ways to feed input, notably stdin
        // CharStreams.fromStream(System.in);

        // 2. user the stream to construct the lexer
        CalcLexer         lexer  = new CalcLexer(input);

        // 3. use the lexer to produce the token stream
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 4. use the token stream to construct the parser
        CalcParser        parser = new CalcParser(tokens);

        // 5. tell the parser to start at the program rule
        ParseTree         tree   = parser.program();

        // 6. make a walker that can visit the nodes in the AST
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

        System.err.println( "answer: " + answer);
    }
}
