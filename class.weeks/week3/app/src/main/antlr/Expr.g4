grammar Expr;
import CommonLexerRules;

program : statement+ ;

statement : 'print' expr NEWLINE                              # printExpr
          | ID '=' expr NEWLINE                               # assign
          | 'if' '(' conditional ')' block else? NEWLINE      # ifStatement
          | NEWLINE                                           # blank
          ;

conditional : expr compare=COMPARISON expr ;

else : 'else' block ;

block : '{' statement* '}' ;

expr : expr op=(MUL|DIV) expr       # MulDiv
     | expr op=(ADD|SUB) expr       # AddSub
     | INT                          # int
     | ID                           # id
     | '(' expr ')'                 # parens
     ;
