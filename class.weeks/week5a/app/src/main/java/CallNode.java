import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CallNode extends Node {
    String functionName;
    List<Node> argNodes;
    EvalVisitor interpretter;

    public CallNode(String functionName, List<Node> argNodes, EvalVisitor interpretter) {
        this.functionName = functionName;
        this.argNodes = argNodes;
        this.interpretter = interpretter;
    }

    @Override
    public void act() {}

    @Override
    public Integer getIntValue() {
        Map<String, Integer> callFrame = interpretter.addCallStackFrame();
        FunctionNode function = interpretter.resolveFunction(functionName);
        List<String> paramNames = function.getParams();
        List<Integer> args = collectArgs();

        for (int i = 0; i < paramNames.size(); i++ ) {
            interpretter.set(paramNames.get(i), args.get(i));
        }

        Integer answer = null;
        try {
            function.invoke();
        }
        catch (ReturnEncounteredException e) {
            answer = interpretter.resolve(ReturnNode.RETURN_SYMBOL);
        }

        interpretter.discardCallStackFrame();

        return answer;
    }

    private List<Integer> collectArgs() {
        List<Integer> answer = new ArrayList<>();

        for (Node argNode : argNodes) {
            answer.add(argNode.getIntValue());
        }

        return answer;
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }
}
