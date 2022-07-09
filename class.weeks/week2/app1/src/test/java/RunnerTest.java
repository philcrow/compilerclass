import org.antlr.v4.runtime.*;
  
import org.junit.*;
import static org.junit.Assert.*;

public class RunnerTest {
    @Test
    public void addTest() {
        String program = "4+5";
        CharStream input = CharStreams.fromString(program);
        Runner runner = new Runner();
        int answer = runner.run(input);
        assertEquals(9, answer);

        program = "-3 + 6";
        input = CharStreams.fromString(program);
        runner = new Runner();
        answer = runner.run(input);
        assertEquals(3, answer);

        program = "-15 + -24";
        input = CharStreams.fromString(program);
        runner = new Runner();
        answer = runner.run(input);
        assertEquals(-39, answer);
    }

    @Test
    public void multiplyTest() {
        String program = "4*5";
        CharStream input = CharStreams.fromString(program);
        Runner runner = new Runner();
        int answer = runner.run(input);
        assertEquals(20, answer);

        program = "52 * -13";
        input = CharStreams.fromString(program);
        runner = new Runner();
        answer = runner.run(input);
        assertEquals(-676, answer);
    }
}
