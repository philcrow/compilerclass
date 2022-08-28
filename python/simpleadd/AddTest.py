from antlr4 import *
from CalcLexer import CalcLexer
from CalcParser import CalcParser
from Adder import Adder
import unittest

class TestAdder(unittest.TestCase):
    input_stream = InputStream("3+4")
    lexer = CalcLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = CalcParser(stream)
    tree = parser.program()
    adder = Adder()
    walker = ParseTreeWalker()
    walker.walk(adder, tree)

    answer = adder.answer
    print(f'answer: {answer}')

if __name__ == '__main__':
    unittest.main()
