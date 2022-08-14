import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.List;

public class PALOperand {
    private String symbol = "";
    private Double doubleValue = 0.0;
    private boolean literal;
    private boolean address = false;
    private boolean direct = true;
    private int increment = 0;
    private boolean incrementPost = false;
    private int registerNumber = -1;
    private PALInterpretter interpretter;

    private PALOperand(PALInterpretter interpretter) {
        this.interpretter = interpretter;
    }

    public static PALOperand getSourceInstance(PALParser.SourceContext source, PALInterpretter interpretter) {
        // figure out which source alternative we have and give that back
        if (source.register() != null) {
            return getRegisterInstance(source.register().REGNUM().getText(), interpretter);
        }
        else {
            return getOperandInstance(source.operand(), interpretter);
        }
    }

    public static PALOperand getRegisterInstance(String register, PALInterpretter interpretter) {
        PALOperand that = new PALOperand(interpretter);
        that.registerNumber = Integer.valueOf(register);
        return that;
    }

    public static PALOperand getOperandInstance(PALParser.OperandContext operand, PALInterpretter interpretter) {
        PALOperand that = new PALOperand(interpretter);
        if (operand.FLOAT() != null) {
            that.literal = true;
            that.doubleValue = Double.valueOf(operand.FLOAT().getText());
        }
        else { // must need a symbol table lookup
            that.literal = false;
            that.handlePointer(operand.value());
        }
        return that;
    }

    public Integer resolveSource() {
        return resolve(false);
    }

    public Integer resolveDestination() {
        // I think it actually an error to try to use a register here
        return resolve(true);
    }

    private Integer resolve(boolean destination) {
        if (destination && address) {
            throw new RuntimeException( "cannot use an address as a destination " + toString());
        }
        Integer symbolAddr = interpretter.getAddrOf(symbol);
        if (direct) {
            return symbolAddr;
        }
        Integer pointerValue = interpretter.getValueIn(symbolAddr).intValue();
        return pointerValue;

    }

    public Double retrieveRegister() {
        return interpretter.retrieveRegister(registerNumber);
    }

    public Double retrieveValue() {
        if (literal) {
            return doubleValue;
        }
        else {
            Integer location = resolveSource();
            if (address) {
                return new Double(location);
            }
            else {
                Integer symbolAddr = interpretter.getAddrOf(symbol);
                if (increment == 0) {
                    return interpretter.getValueIn(location);
                }
                else if (incrementPost) {
                    Double answer = interpretter.getValueIn(location);
                    interpretter.putPointerIn(symbolAddr, location + increment);
                    return answer;
                }
                else {
                    location += increment;
                    interpretter.putPointerIn(symbolAddr, location);
                    return interpretter.getValueIn(location);
                }
            }
        }
    }

    // Methods for parsing below

    public static List<PALOperand> getInstances(List<PALParser.OperandContext> operands, PALInterpretter interpretter) {
        List<PALOperand> answer = new ArrayList<>();
        for (PALParser.OperandContext operand : operands) {
            answer.add(PALOperand.getOperandInstance(operand, interpretter));
        }
        return answer;
    }

    private void handlePointer(PALParser.ValueContext value) {
        if (value.IDENT() != null) {
            symbol = value.IDENT().getText();
        }
        else if (value.address() != null) {
            symbol = value.address().IDENT().getText();
            address = true;
        }
        else {
            ParseTreeWalker walker = new ParseTreeWalker();
            OPListener listener = new OPListener(this);

            for (ParseTree child : value.children) {
                walker.walk(listener, child);
            }
        }
    }

    public int getPointer() {
        return 0;
    }

    public Double getValue() {
        return 0.0;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public void setPointerValues(String symbol, int increment, boolean incrementPost) {
        direct = false;
        this.symbol = symbol;
        this.increment = increment;
        this.incrementPost = incrementPost;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (address) {
            sb.append("&");
        }

        if (! direct) {
            sb.append("@");
        }

        if (increment != 0 && ! incrementPost) {
            if (increment < 0) {
                sb.append("-");
            }
            else {
                sb.append("+");
            }
        }

        sb.append(symbol);

        if (increment != 0 && incrementPost) {
            if (increment < 0) {
                sb.append("-");
            }
            else {
                sb.append("+");
            }
        }

        return sb.toString();
    }
}
