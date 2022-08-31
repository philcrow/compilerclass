from antlr4 import *
from ExprLexer import ExprLexer
from ExprParser import ExprParser
import unittest

class TestExpr(unittest.TestCase):
    def test_adding(self):
        input_stream = InputStream("3+4")
        lexer = ExprLexer(input_stream)
        stream = CommonTokenStream(lexer)
        parser = ExprParser(stream)
        tree = parser.program()
        interpreter = Interpreter()
        answer = interpreter.visit()
        self.assertEquals(7, answer)

# add more tests

if __name__ == '__main__':
    unittest.main()
