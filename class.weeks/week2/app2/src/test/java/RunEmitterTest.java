import org.antlr.v4.runtime.*;
  
import org.junit.*;
import static org.junit.Assert.*;

public class RunEmitterTest {
    @Test
    public void assignTest() {
        String program = "x = 4\n3 + x * 2";
        CharStream input = CharStreams.fromString(program);
        RunEmitter runner = new RunEmitter();
        String answer = runner.run(input);
        assertEquals("3\n4\n2\n*\n+\np\n", answer);
    }
}
