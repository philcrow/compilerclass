// requires all operations to take place in registers
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
add       : 'add' source register ;
branch    : operator left=operand right=operand dest=operand;
div       : 'div' source register ;
end       : 'end' INT ;
jump      : 'jump' operand;
gosub     : 'gosub' operand ;
multiply  : 'mult' source register ;
prompt    : 'prompt' STRING ;
print     : 'prt' source+ ;
return    : 'ret' ;
shower    : 'shower' ;
move      : 'move' source source ;
subtract  : 'subt' source register ;
take      : 'take' operand ;

source : operand | register ;
register : '%reg' REGNUM ;
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
REGNUM: [A-F] ;
FLOAT: [0-9]* '.' [0-9]+ ;
STRING: '"' .*? '"' ;
PLUS : '+' ;
MINUS : '-' ;
NL: '\r'? '\n' ;
WS: [ \t] -> skip ;
