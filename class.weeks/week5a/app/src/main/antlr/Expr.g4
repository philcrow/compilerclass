grammar Expr;
import CommonLexerRules;

program : statement+ ;

statement : 'print' expr NEWLINE                    # printExpr
          | ID '=' expr NEWLINE                     # assign
          | 'if' '(' conditional ')' block else?    # ifStatement
          | 'while' '(' conditional ')' block       # whileStatement
          | 'function' ID '(' paramList? ')' block  # functionDefinition
          | 'return' expr NEWLINE                   # return
          | NEWLINE                                 # blank
          ;

conditional : expr compare=COMPARISON expr ;

else : 'else' block ;

block : '{' statement* '}' NEWLINE? ;

paramList : ID ',' paramList
          | ID
          ;

expr : expr op=(MUL|DIV) expr       # MulDiv
     | expr op=(ADD|SUB) expr       # AddSub
     | INT                          # int
     | ID '(' exprList? ')'         # call
     | ID                           # id
     | '(' expr ')'                 # parens
     ;

exprList : expr ',' exprList
         | expr
         ;
