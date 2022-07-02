import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Runner {
    public static void main(String[] args) throws Exception {
        // Standard Process, can always be used, but the method called
        // on the parser is our top level rule
        // 1. get the input into a stream from a file passed on command line
        CharStream        input  = CharStreams.fromFileName(args[0]);
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
    }
}
