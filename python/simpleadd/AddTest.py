from antlr4 import *
from CalcLexer import CalcLexer
from CalcParser import CalcParser
from Adder import Adder
import unittest

class TestAdder(unittest.TestCase):
    def test_adding(self):
        input_stream = InputStream("3+4")
        lexer = CalcLexer(input_stream)
        stream = CommonTokenStream(lexer)
        parser = CalcParser(stream)
        tree = parser.program()
        adder = Adder()
        walker = ParseTreeWalker()
        walker.walk(adder, tree)

        answer = adder.answer
        self.assertEqual(7, answer)

if __name__ == '__main__':
    unittest.main()
