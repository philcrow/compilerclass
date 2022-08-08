lexer grammar CommonLexerRules;

MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
FLOAT: '-'? ( '.' [0-9]+ | '0' '.' [0-9]+ | [1-9][0-9]* '.' [0-9]* ) ;
COMPARISON : '==' | '!=' | '<' | '>' | '<=' | '>=' ;
NEWLINE : '\r'? '\n' ;
WS : [ \t] -> skip ;

