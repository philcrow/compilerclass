from antlr4 import *
from CalcLexer import CalcLexer
from CalcParser import CalcParser
from VisitAdder import VisitAdder
import unittest

class TestVisitAdder(unittest.TestCase):
    def test_adding(self):
        input_stream = InputStream("3+4")
        lexer = CalcLexer(input_stream)
        stream = CommonTokenStream(lexer)
        parser = CalcParser(stream)
        tree = parser.program()
        visitAdder = VisitAdder()
        answer = visitAdder.visit(tree)
        self.assertEqual(7, answer)

if __name__ == '__main__':
    unittest.main()
