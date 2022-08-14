import org.antlr.v4.runtime.*;

import java.util.*;
  
import org.junit.*;
import static org.junit.Assert.*;

public class RunnerTest {
    @Test
    public void interpretterTests() throws Exception {
        List<String> programLines = new ArrayList<>();
        programLines.add("x: alloc 1");
        programLines.add("y: alloc 1");
        programLines.add("   move 4.0   x"    );
        programLines.add("   move 5.0   y"    );
        programLines.add("   move x     %regA");
        programLines.add("   add  y     %regA");
        programLines.add("   move %regA x"    );
        String program = String.join("\n", programLines) + "\n";
        //System.err.println( "Running program:\n" + program);
        CharStream input = CharStreams.fromString(program);
        PALInterpretter interpretter = PALRunner.run(input);

        Double answer = interpretter.retrieveRegister("A");
        //System.err.println( "answer " + answer);
        assertEquals(9, answer, .001);
    }
}
