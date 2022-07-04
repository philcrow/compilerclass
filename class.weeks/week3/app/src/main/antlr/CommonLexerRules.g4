lexer grammar CommonLexerRules;

ID : [a-zA-Z]+ ;
INT : '-'? [0-9]+ ;
COMPARISON : '==' | '!=' | '<' | '>' | '<=' | '>=' ;
NEWLINE : '\r'? '\n' ;
WS : [ \t] -> skip ;

MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
