import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public interface EvalVisitor<T> extends ExprVisitor<T> {

    public String emit(Node program);

    // Helpers

    public Integer resolve(String symbol);

    public void set(String symbol, Integer newValue);

    public String getFreeRegister();

    public void freeRegister(String register);
}
