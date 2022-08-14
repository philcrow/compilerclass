import org.antlr.v4.runtime.*;

import java.util.*;
  
import org.junit.*;
import static org.junit.Assert.*;

public class RunnerTest {
    @Test
    public void addTest() throws Exception {
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

    @Test
    public void payrollTest() throws Exception {
        List<String> programLines = new ArrayList<>();
        programLines.add("hrs:        alloc   1");
        programLines.add("rate:       alloc   1");
        programLines.add("pay:        alloc   1");

        programLines.add("            move    43.0  hrs");
        programLines.add("            move    12.0  rate");
        programLines.add("            brgt    hrs     40.0    overtime");
        programLines.add("regular:    move    hrs     %regA");
        programLines.add("            mult    rate    %regA");
        programLines.add("            move    %regA   pay");
        programLines.add("            jump    finish");
        programLines.add("overtime:   move    40.0    %regA");
        programLines.add("            mult    rate    %regA");
        programLines.add("            move    hrs     %regB");
        programLines.add("            subt    40.0    %regB");
        programLines.add("            mult    rate    %regB");
        programLines.add("            mult    1.5     %regB");
        programLines.add("            add     %regB   %regA");
        programLines.add("            move    %regA   pay");
        programLines.add("finish:     prt     pay");
        String program = String.join("\n", programLines) + "\n";
        System.err.println( program );
        CharStream input = CharStreams.fromString(program);
        PALInterpretter interpretter = PALRunner.run(input);
        Double answer = interpretter.getValueIn(2);

        assertEquals(534, answer, .001);
    }

    @Test
    public void factorialTest() throws Exception {
        List<String> programLines = new ArrayList<>();
        programLines.add("n:         alloc      1");
        programLines.add("answer:    alloc      1");

        programLines.add("           move       5.0     n"); // initial value
        programLines.add("           move       1.0     %regB");
        programLines.add("           gosub      fact");
        programLines.add("           move       %regB   answer");
        programLines.add("           jump       ending");

        programLines.add("fact:      brle       n     1.0     base");
        programLines.add("           mult       n     %regB");
        programLines.add("           move       n     %regA");
        programLines.add("           subt       1.0   %regA");
        programLines.add("           move       %regA n");
        programLines.add("           gosub      fact");
        programLines.add("base:      ret");

        programLines.add("ending:    prt        answer");

        String program = String.join("\n", programLines) + "\n";
        System.err.println( program );
        CharStream input = CharStreams.fromString(program);
        PALInterpretter interpretter = PALRunner.run(input);
        Double answer = interpretter.getValueIn(1);

        assertEquals(120, answer, .001);
    }
}
