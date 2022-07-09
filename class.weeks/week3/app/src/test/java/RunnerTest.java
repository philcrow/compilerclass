import org.antlr.v4.runtime.*;
  
import org.junit.*;
import static org.junit.Assert.*;

public class RunnerTest {
    @Test
    public void addTest() {
        String program = "print 4+5\n";
        CharStream input = CharStreams.fromString(program);
        Runner runner = new Runner();
        Node answer = runner.run(input);
        System.err.println( "Node: " + answer);
        // we could descend the tree here to see if the structure is right
    }
}
