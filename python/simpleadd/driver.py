import sys
from antlr4 import *
from CalcLexer import CalcLexer
from CalcParser import CalcParser
from Adder import Adder

def main(argv):
    input_stream = FileStream(argv[1])
    lexer = CalcLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = CalcParser(stream)
    tree = parser.program()
    adder = Adder()
    walker = ParseTreeWalker()
    walker.walk(adder, tree)

if __name__ == '__main__':
    main(sys.argv)
