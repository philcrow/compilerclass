import org.antlr.v4.runtime.*;

import org.junit.*;
import static org.junit.Assert.*;

public class RunnerTest {
    @Test
    public void interpretterTests() {
        String program = "4+5";
        CharStream input = CharStreams.fromString(program);
        Runner runner = new Runner();
        int answer = runner.run(input);
        assertEquals(9, answer);
    }
}
