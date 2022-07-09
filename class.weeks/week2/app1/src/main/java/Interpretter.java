import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class Interpretter extends CalcBaseListener {
    int answer = -1;
	@Override
    public void enterMulitply(CalcParser.MulitplyContext ctx) {
        List<TerminalNode> numberNodes = ctx.NUMBER();
        answer = 1;
        for (TerminalNode node : numberNodes) {
            answer *= Integer.valueOf(node.getText());
        }
    }

	@Override
    public void enterAdd(CalcParser.AddContext ctx) {
        List<TerminalNode> numberNodes = ctx.NUMBER();
        answer = 0;
        for (TerminalNode node : numberNodes) {
            answer += Integer.valueOf(node.getText());
        }
    }

    public int getAnswer() {
        return answer;
    }
}
