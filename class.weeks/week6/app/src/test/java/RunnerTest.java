import org.antlr.v4.runtime.*;

import org.apache.commons.io.IOUtils;

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
    public void typeTest() throws Exception {
        String program = IOUtils.toString(
            this.getClass().getResourceAsStream("function.program"), "UTF-8"
        );
        Node answer = run(program);

        String dumpedProgram = IOUtils.toString(
            this.getClass().getResourceAsStream("function.program.dumped"), "UTF-8"
        );

        assertEquals(dumpedProgram, answer.toString());
    }

    @Test
    public void subtractionTest() {
        String program = "print 8-7\n";
        Node answer = run(program);

        assertEquals("[print [subtract INT 8 - INT 7]]\n", answer.toString());
    }

    @Test
    public void subtractionNegativeTest() {
        String program = "print 8--7\n";
        Node answer = run(program);

        assertEquals("[print [subtract INT 8 - NEGATED INT 7]]\n", answer.toString());
    }
}
