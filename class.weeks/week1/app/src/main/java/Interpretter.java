import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class Interpretter extends CalcBaseListener {
    int answer = 0;

	@Override
    public void enterProgram(CalcParser.ProgramContext ctx) {
        List<TerminalNode> numberNodes = ctx.NUMBER();
        for (TerminalNode node : numberNodes) {
            answer += Integer.valueOf(node.getText());
        }
    }

    public int getAnswer() {
        return answer;
    }
}
