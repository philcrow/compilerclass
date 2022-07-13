grammar Expr;
import CommonLexerRules;

program : statement+ ;

statement : 'print' expr NEWLINE                            # printExpr
          | type ID NEWLINE                                 # declare
          | type ID '=' expr NEWLINE                        # declareAssign
          | ID '=' expr NEWLINE                             # assign
          | 'if' '(' conditional ')' block else?            # ifStatement
          | 'while' '(' conditional ')' block               # whileStatement
          | 'function' ID '(' paramList? ')' ':' type block # functionDefiniton
          | 'return' expr                                   # return
          | NEWLINE                                         # blank
          ;

type : 'int' | 'float' ;

conditional : expr compare=COMPARISON expr ;

else : 'else' block ;

block : '{' statement* '}' NEWLINE? ;

paramList : param ',' paramList
          | param
          ;

param : type ID ;

expr : expr op=(MUL|DIV) expr       # MulDiv
     | expr op=(ADD|SUB) expr       # AddSub
     | INT                          # int
     | FLOAT                        # float
     | ID '(' exprList? ')'         # call
     | ID                           # id
     | '(' expr ')'                 # parens
     ;

exprList : expr ',' exprList
         | expr
         ;
