grammar Expr;
import CommonLexerRules;

program : statement+ ;

statement : expr NEWLINE            # printExpr
          | ID '=' expr NEWLINE     # assign
          | NEWLINE                 # blank
          ;

expr : expr op=('*'|'/') expr       # MulDiv
     | expr op=('+'|'-') expr       # AddSub
     | INT                          # int
     | ID                           # id
     | '(' expr ')'                 # parens
     ;
