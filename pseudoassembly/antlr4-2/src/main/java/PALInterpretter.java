import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PALInterpretter extends PALBaseListener {
    // instructions can have a label, the specific statement might need that
    String currentLabel;

    // the registers
    Map<String, Double> registers;

    // every alloc will add element(s) to this segment
    List<Double> memorySegment;

    // stores the commands ready for interpretting
    List<PALNode> commands;

    // your Node types are at least:
    // Alloc, Add, Branch, Div, End, Jump, Gosub, Multiply, Prompt, Print, Return
    // Shower, Move, Subtract, Take
    // The nodes themselves are responsible to store their operands and the act.

    // every alloc will store the symbol as the key
    // and the element number in memorySegment in the value
    // likewise, all labeled commands will store their label and line number for jumps
    Map<String, Integer> symbolTable;

    // gosub is like jump, except that it pushes the program pointer onto this stack
    // before going
    // ret is responsible for popping that pointer
    List<Integer> callStack;
    Integer programPointer;

    public PALInterpretter() {
        currentLabel = "";
        memorySegment = new ArrayList<>();
        commands = new ArrayList<>();
        symbolTable = new HashMap<>();
        callStack = new ArrayList<Integer>();
        registers = new HashMap<String, Double>() {{
            put("A", 0.0);
            put("B", 0.0);
            put("C", 0.0);
            put("D", 0.0);
            put("E", 0.0);
            put("F", 0.0);
        }};
    }

    public void run() {
        programPointer = 0;
        while (getProgramPointer() < commands.size()) {
            PALNode currentCommand = commands.get(getProgramPointer());
            currentCommand.act();
            incrementProgramPointer();
        }
    }

    public void shower() {
        System.err.println( "memory: " + memorySegment);
    }

    /**
     * Actually sets the program pointer to the line before the supplied value, because the main loop always increments.
     * @param newValue exactly where you want to land
     */
    public void setProgramPointer(Integer newValue) {
        programPointer = newValue - 1;
    }

    public void incrementProgramPointer() {
        programPointer++;
    }

    public Integer getProgramPointer() {
        return programPointer;
    }

    public void pushCallStack() {
        callStack.add(getProgramPointer());
    }

    public void popCallStack() {
        programPointer = callStack.remove(callStack.size() - 1);
    }

    public void storeValue(PALOperand destOp, Double value) {
        if (destOp.isRegister()) {
            String dest = destOp.getRegisterNumber();
            registers.put(dest, value);
        }
        else {
            Integer dest = destOp.resolveDestination();
            memorySegment.set(dest, value);
        }
    }

    // methods to help PALOperand resolve locations
    public Integer getAddrOf(String symbol) {
        return symbolTable.get(symbol);
    }

    public Double getValueIn(Integer location) {
        return memorySegment.get(location);
    }

    public void putPointerIn(Integer location, Integer newPointer) {
        memorySegment.set(location, new Double(newPointer));
    }

    public Double retrieveRegister(String register) {
        return registers.get(register);
    }

    public void storeRegister(String register, Double value) {
        registers.put(register, value);
    }

    // parser listener methods below

    @Override
    public void exitProgram(PALParser.ProgramContext ctx) {
        //System.err.println( "assembly complete " + labelPosition + " " + memorySegment + " " + symbolTable);
        //for (PALNode command : commands) {
        //    System.out.println( command.toString() );
        //}
    }

    @Override
    public void enterInstruction(PALParser.InstructionContext ctx) {
        String newLabel = null;
        if (ctx.label() != null) {
            newLabel = ctx.label().IDENT().getText();
            //labelPosition.put(newLabel, commands.size());
            symbolTable.put(newLabel, commands.size());
        }
        currentLabel = newLabel;

        if (ctx.command() == null) {
            PALNode blankLineNode = new PALNode(newLabel);
            commands.add(blankLineNode);
        }
    }

    @Override
    public void enterAlloc(PALParser.AllocContext ctx) {
        Integer size = Integer.valueOf(ctx.INT().getText());

        symbolTable.put(currentLabel, Integer.valueOf(memorySegment.size()));
        for (int i = 0; i < size; i++) {
            memorySegment.add(0.0);
        }
        commands.add(new PALAllocNode(currentLabel, size));
    }

    @Override
    public void enterAdd(PALParser.AddContext ctx) {
        PALOperand op1 = PALOperand.getSourceInstance(ctx.source(), this);
        PALOperand op2 = PALOperand.getRegisterInstance(ctx.register().REGNUM().getText(), this);

        commands.add(new PALAddNode(currentLabel, op1, op2, this));
    }

    @Override
    public void enterSubtract(PALParser.SubtractContext ctx) {
        PALOperand op1 = PALOperand.getSourceInstance(ctx.source(), this);
        PALOperand op2 = PALOperand.getRegisterInstance(ctx.register().REGNUM().getText(), this);

        commands.add(new PALSubtractNode(currentLabel, op1, op2, this));
    }

    @Override
    public void enterMultiply(PALParser.MultiplyContext ctx) {
        PALOperand op1 = PALOperand.getSourceInstance(ctx.source(), this);
        PALOperand op2 = PALOperand.getRegisterInstance(ctx.register().REGNUM().getText(), this);

        commands.add(new PALMultiplyNode(currentLabel, op1, op2, this));
    }

    @Override
    public void enterDiv(PALParser.DivContext ctx) {
        PALOperand op1 = PALOperand.getSourceInstance(ctx.source(), this);
        PALOperand op2 = PALOperand.getRegisterInstance(ctx.register().REGNUM().getText(), this);

        commands.add(new PALDivideNode(currentLabel, op1, op2, this));
    }

    @Override
    public void enterBranch(PALParser.BranchContext ctx) {
        PALOperand op1 = PALOperand.getSourceInstance(ctx.left, this);
        PALOperand op2 = PALOperand.getSourceInstance(ctx.right, this);
        PALOperand op3 = PALOperand.getOperandInstance(ctx.dest, this);

        commands.add(new PALBranchNode(
            currentLabel,
            ctx.operator().getText().substring(2), 
            op1, op2, op3,
            this));
    }

    @Override
    public void enterEnd(PALParser.EndContext ctx) {
        commands.add(new PALEndNode(currentLabel, Integer.valueOf(ctx.INT().getText())));
    }

    @Override
    public void enterMove(PALParser.MoveContext ctx) {
        PALOperand source = PALOperand.getSourceInstance(ctx.source(0), this);
        PALOperand destination = PALOperand.getSourceInstance(ctx.source(1), this);
        commands.add(new PALMoveNode(currentLabel, source, destination, this));
    }

    @Override
    public void enterPrint(PALParser.PrintContext ctx) {
        commands.add(new PALPrintNode(currentLabel, PALOperand.getInstances(ctx.source(), this), this));
    }

    @Override
    public void enterPrompt(PALParser.PromptContext ctx) {
        commands.add(new PALPromptNode(currentLabel, ctx.STRING().getText()));
    }

    @Override
    public void enterTake(PALParser.TakeContext ctx) {
        commands.add(
            new PALTakeNode(
                currentLabel,
                PALOperand.getOperandInstance(ctx.operand(), this),
                this));
    }

    @Override
    public void enterJump(PALParser.JumpContext ctx) {
        commands.add(
            new PALJumpNode(
                currentLabel,
                PALOperand.getOperandInstance(ctx.operand(), this),
                this));
    }

    @Override
    public void enterGosub(PALParser.GosubContext ctx) {
        commands.add(
            new PALGosubNode(
                currentLabel,
                PALOperand.getOperandInstance(ctx.operand(), this),
                this));
    }

    @Override
    public void enterReturn(PALParser.ReturnContext ctx) {
        commands.add(new PALRetNode(currentLabel, this));
    }

    @Override public void enterShower(PALParser.ShowerContext ctx) {
        commands.add(new PALShowerNode(currentLabel, this));
    }
}
