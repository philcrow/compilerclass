import org.antlr.v4.runtime.*;

import org.apache.commons.io.IOUtils;

import org.junit.*;
import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class ErrorMessageTests {

    private Node run(String program) {
        CharStream input = CharStreams.fromString(program);
        Runner runner = new Runner();
        Node answer = runner.run(input);
        return answer;
    }

    @Test
    public void idNotDeclaredTest() throws Exception {
        String program = "print 4 + 4\nprint 8 - 7\nx = 3.2\n";
        try {
            Node answer = run(program);
            fail("use of undeclared variable not seen");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), containsString("ID x must be declared"));
            assertThat(e.getMessage(), containsString("at line 3"));
        }
    }

    @Test
    public void floatToIntAssignmentTest() throws Exception {
        String program = "int x\nx = 3.2\n";
        try {
            Node answer = run(program);
            fail("cannot assign float to int exception not seen");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), containsString("cannot assign float to int"));
            assertThat(e.getMessage(), containsString("at line 2"));
        }
    }

    @Test
    public void useIntBeforeAssignTest() throws Exception {
        String program = "int x\nx = x + 6\n";
        try {
            Node answer = run(program);
            fail("cannot use int var before giving it a value not seen");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), containsString("Symbol 'x' has no value"));
            assertThat(e.getMessage(), containsString("at line 2"));
        }
    }

    @Test
    public void useFloatBeforeAssignTest() throws Exception {
        String program = "float x\nx = x + 6.2\n";
        try {
            Node answer = run(program);
            fail("cannot use int var before giving it a value not seen");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), containsString("Symbol 'x' has no value"));
            assertThat(e.getMessage(), containsString("at line 2"));
        }
    }

    @Test
    public void setFloatInInt() throws Exception {
        String program = "int x\nx = x + 6.2\n";
        try {
            Node answer = run(program);
            fail("cannot assign float to int not seen");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), containsString("cannot assign float to int x"));
            assertThat(e.getMessage(), containsString("at line 2"));
        }
    }

    @Test
    public void getIntFromFloat() throws Exception {
        String program = "int x\nfloat y\nx = y + 2\n";
        try {
            Node answer = run(program);
            fail("symbol is float not int not seen");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), containsString("cannot assign float to int x"));
            assertThat(e.getMessage(), containsString("at line 3"));
        }
    }

    @Test
    public void getIntFromFloatId() throws Exception {
        String program = "int x\nfloat y = 1.5\nfloat z = 2.3\nx = y / z\n";
        try {
            Node answer = run(program);
            fail("symbol is float not int not seen");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), containsString("cannot assign float to int x"));
            assertThat(e.getMessage(), containsString("at line 4"));
        }
    }

    @Test
    public void assignGetIntFromFloat() throws Exception {
        String program = "int x\nfloat y = 1.5\nfloat z = 2.3\nx = y / z\n";
        try {
            Node answer = run(program);
            fail("symbol is float not int not seen");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), containsString("cannot assign float to int x"));
            assertThat(e.getMessage(), containsString("at line 4"));
        }
    }
}
