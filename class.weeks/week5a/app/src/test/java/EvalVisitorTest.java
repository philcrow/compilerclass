import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
  
import org.apache.commons.io.IOUtils;

import org.junit.*;
import static org.junit.Assert.*;

public class EvalVisitorTest {
    @Test
    public void functionTest() throws Exception {
        String program = IOUtils.toString(
            this.getClass().getResourceAsStream("function.program"),
            "UTF-8"
        );
        CharStream input = CharStreams.fromString(program);
        ExprLexer         lexer  = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser        parser = new ExprParser(tokens);
        ParseTree         tree   = parser.program();

        EvalVisitor eval = new EvalVisitor();
        Node nodeTree = eval.visit(tree);
        nodeTree.act();

        assertEquals(Integer.valueOf(445), eval.resolve("paybob"));
        assertEquals(Integer.valueOf(624), eval.resolve("payjan"));
    }
}
