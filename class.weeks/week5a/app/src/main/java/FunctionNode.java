import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionNode extends Node {
    List<String> params;
    Node block;

    public FunctionNode(List<String> params, Node block) {
        this.params = params;
        this.block = block;
    }

    public List<String> getParams() {
        return params;
    }

    @Override
    public void act() { }

    public void invoke() {
        block.act();
    }

    @Override
    public Integer getIntValue() {
        return 0;
    }

    @Override
    public boolean getBooleanValue() {
        return getIntValue() != 0;
    }
}
