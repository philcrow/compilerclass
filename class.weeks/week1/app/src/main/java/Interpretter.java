import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Interpretter extends CalcBaseListener {
    int answer = 0;

	@Override
    public void enterProgram(CalcParser.ProgramContext ctx) {
        int left = Integer.valueOf(ctx.NUMBER(0).getText());
        int right = Integer.valueOf(ctx.NUMBER(1).getText());
        answer = left + right;
    }

    public int getAnswer() {
        return answer;
    }
}
