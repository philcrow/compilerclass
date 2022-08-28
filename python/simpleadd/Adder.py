from CalcListener import CalcListener

class Adder(CalcListener):
    def enterProgram(self, ctx):
        left = int(ctx.NUMBER(0).getText())
        right = int(ctx.NUMBER(1).getText())
        self.answer = left + right
        print(f'entering program {left} + {right} = {self.answer}')
