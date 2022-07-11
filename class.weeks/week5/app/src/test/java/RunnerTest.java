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
            this.getClass().getResourceAsStream("type.program"), "UTF-8"
        );
        Node answer = run(program);

        String dumpedProgram = IOUtils.toString(
            this.getClass().getResourceAsStream("type.program.dumped"), "UTF-8"
        );

        assertEquals(dumpedProgram, answer.toString());
    }
}
