grammar Calc;

program : NUMBER '*' NUMBER  # Mulitply
        | NUMBER '+' NUMBER  # Add
        ;

NUMBER: '-'? [0-9]+ ;
WS: [ \t\n\r] -> skip ;
