grammar Expr;

program : statement+ ;

statement : expr NEWLINE            # printExpr
          | ID '=' expr NEWLINE     # assign
          | NEWLINE                 # blank
          ;

expr : expr op=(STAR|SLASH) expr    # MulDiv
     | expr op=(PLUS|MINUS) expr    # AddSub
     | INT                          # int
     | ID                           # id
     | '(' expr ')'                 # parens
     ;

ID : [a-zA-Z]+ ;
INT : '-'? [0-9]+ ;
NEWLINE : '\r'? '\n' ;
WS : [ \t] -> skip ;

STAR : '*' ;
SLASH : '/' ;
PLUS : '+' ;
MINUS : '-' ;
