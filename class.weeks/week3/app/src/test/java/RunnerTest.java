import org.antlr.v4.runtime.*;
  
import org.junit.*;
import static org.junit.Assert.*;

public class RunnerTest {

    private Node run(String program) {
        CharStream input = CharStreams.fromString(program);
        Runner runner = new Runner();
        Node answer = runner.run(input);
        return answer;
    }

    @Test
    public void addTest() {
        String program = "print 4+5\n";
        Node answer = run(program);
        String tree = answer.toString();
        assertEquals("[print [Add INT 4 + INT 5]]", tree);
    }

    @Test
    public void varTest() {
        String program = "x = 4\nprint 3 + x * 2\n";
        Node answer = run(program);
        String tree = answer.toString();
        assertEquals("[assign x INT 4][print [Add INT 3 + multiply id x * INT 2]]", tree);
    }
}
