grammar Expr;
import CommonLexerRules;

program : statement+ ;

statement : 'print' expr NEWLINE                    # printExpr
          | ID '=' expr NEWLINE                     # assign
          | 'if' '(' conditional ')' block else?    # ifStatement
          | 'while' '(' conditional ')' block       # whileStatement
          | NEWLINE                                 # blank
          ;

conditional : expr compare=COMPARISON expr ;

else : 'else' block ;

block : '{' statement* '}' NEWLINE? ;

expr : expr op=(MUL|DIV) expr       # MulDiv
     | expr op=(ADD|SUB) expr       # AddSub
     | INT                          # int
     | ID                           # id
     | '(' expr ')'                 # parens
     ;
