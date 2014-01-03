grammar Paradox;

root: key? game EOF ;

game: pair+ ;

pair: key '=' value;

value
    : string
    | number
    | object
    | array
    | date
    | bool
    | enumerated
    | empty
    ;


key
    : ID
    | INTEGER
    | DATE
    | DASHES
    ;


date
    : DATE
    | QUOTEDDATE
    ;

string: STRING ;


number
    : FLOATING
    | INTEGER
    ;

bool
    : YES
    | NO
    ;

YES: 'yes' ;

NO: 'no' ;

FLOATING: '-'? [0-9]+ '.' [0-9]+ ;

INTEGER: '-'? [0-9]+ ;

DATE: [0-9]+ '.' [0-9]+ '.' [0-9]+ ;

QUOTEDDATE: '"' DATE '"' ;

array: '{' value+ '}' ;

object: '{' (pair | empty)+ '}' ; // Re: having empty here -- apparently "x=y\nz=w\n{}" is valid

empty: '{' '}' ;

enumerated : key ;

STRING: '"' (.)*? '"' ;

DASHES: '-'+ ;

ID : [a-zA-Z0-9_]+ ;

WS : [ \r\t\n]+ -> skip ;

