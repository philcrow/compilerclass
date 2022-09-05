from CalcVisitor import CalcVisitor

class VisitAdder(CalcVisitor):
    def visitProgram(self, ctx):
        left = int(ctx.NUMBER(0).getText())
        right = int(ctx.NUMBER(1).getText())
        answer = left + right
        print(f"{left} + {right} = {answer}")
        return answer

