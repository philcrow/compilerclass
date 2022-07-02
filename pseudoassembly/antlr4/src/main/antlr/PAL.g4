grammar PAL;

program : (instruction NL)* ;

instruction : label? command
            | WS* ;

label : IDENT ':' ;

command : alloc
        | add
        | branch
        | div
        | end
        | jump
        | gosub
        | multiply
        | prompt
        | print
        | return
        | shower
        | move
        | subtract
        | take ;

alloc     : 'alloc' INT ;
add       : 'add' operand operand ;
branch    : operator left=operand right=operand dest=operand;
div       : 'div' operand operand ;
end       : 'end' INT ;
jump      : 'jump' operand;
gosub     : 'gosub' operand ;
multiply  : 'mult' operand operand ;
prompt    : 'prompt' STRING ;
print     : 'prt' operand+ ;
return    : 'ret' ;
shower    : 'shower' ;
move      : 'move' operand operand ;
subtract  : 'subt' operand operand ;
take      : 'take' operand ;

operand : value | FLOAT ;
operator : 'breq' | 'brge' | 'brgt' | 'brle' | 'brlt' | 'brne' ;

value : IDENT | pointer | address ;

pointer : '@' increment IDENT   # preincrementPointer
        | '@' IDENT increment   # postincrementPointer
        | '@' IDENT             # noincrementPointer
        ;

address : '&' IDENT ;

increment : PLUS | MINUS ;

IDENT: [a-z]+ ;
INT: [0-9]+ ;
FLOAT: [0-9]* '.' [0-9]+ ;
STRING: '"' .*? '"' ;
PLUS : '+' ;
MINUS : '-' ;
NL: '\r'? '\n' ;
WS: [ \t] -> skip ;
