public class OPListener extends PALBaseListener {
    PALOperand operand;

    public OPListener(PALOperand operand) {
        this.operand = operand;
    }

    @Override
    public void enterPreincrementPointer(PALParser.PreincrementPointerContext ctx) {
        int increment = (ctx.increment().PLUS() != null) ? 1 : -1;
        operand.setPointerValues(ctx.IDENT().getText(), increment, false);
    }
    @Override
    public void enterPostincrementPointer(PALParser.PostincrementPointerContext ctx) {
        int increment = (ctx.increment().PLUS() != null) ? 1 : -1;
        operand.setPointerValues(ctx.IDENT().getText(), increment, true);
    }
    @Override
    public void enterNoincrementPointer(PALParser.NoincrementPointerContext ctx) {
        operand.setPointerValues(ctx.IDENT().getText(), 0, false);
    }
}
