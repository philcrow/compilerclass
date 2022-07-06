lexer grammar CommonLexerRules;

ID : [a-zA-Z]+ ;
INT : '-'? [0-9]+ ;
FLOAT : '-'? [0-9]+ '.' | '-'? '.' INT+ | '-'? INT+ '.' INT+ ;
COMPARISON : '==' | '!=' | '<' | '>' | '<=' | '>=' ;
NEWLINE : '\r'? '\n' ;
WS : [ \t] -> skip ;

MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
