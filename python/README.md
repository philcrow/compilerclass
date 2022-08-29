# Using antlr with python3

## Get Python

You need python3 with pip3.
Start with python. Visit https://www.python.org/downloads/windows/.
Use the 64-bit Windows installer. This should include pip (the Python
install program).

You can alias those as python and pip.

## Get the ANTLR runtime for Python

pip3 install antlr4-python3-runtime

## Making antlr do something

To work on a project:

1. Make a directory (folder).
2. Create a grammar file in the directory Calc.g4. Capitalize this name, use CammelCase, end with .g4
3. Put a grammar in that file. (See below for an example grammar.)
4. Invoke antlr: `antlr -Dlanguage=Python3 Calc.g4`
5. See [the antlr python project](https://github.com/antlr/antlr4/blob/master/doc/python-target.md). You need two files to have a listener: a driver to read the file and direct the antlr process and a listener to do your specific work. (See below for samples.) Note that things are slightly different if you use a visitor.
6. Use the ctx object in the enterProgram method to do the work.

## Sample Grammar

In a file called Calc.g4, type this in a text editor:

    grammar Calc;
    program: NUMBER '+' NUMBER ;
    NUMBER: [0-9] ;

## Sample listener

In a file called Adder.py, type this in a text editor:

    from CalcListener import CalcListener
    
    class Adder(CalcListener):
        def enterProgram(self, ctx):
            left = int(ctx.NUMBER(0).getText())
            right = int(ctx.NUMBER(1).getText())
            answer = left + right
            print(f'entering program {left} + {right} = {answer}')

## Sample driver for a listener

In a file called driver.py, type this in a text editor:

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

## Running the Samples

Create one final file called program and type this into it in a text editor:

    3+4

Run the program. At a command line, type:

    antlr4 Calc.g4 -Dlanguage=Python3
    python3 driver.py program

Note that your antlr4 may be available as antlr and python3 may be available as just python.

## Finding ANTLR Source Code

If you want to know what the python antlr runtime system provides,
use the python repl:

    python3

That gives a little introduction including the version of python you
are using. Then, it shows a prompt: `>>>`.

At the prompt type:

    import antlr4
    antlr4.__file__

That will print the path to the location of the files.
