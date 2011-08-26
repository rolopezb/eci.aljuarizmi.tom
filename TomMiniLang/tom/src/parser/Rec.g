grammar Rec;
options {
  output=AST;
  ASTLabelType=Tree;
  tokenVocab=RecTokens;
}
 
@header {
  package parser;
}
@lexer::header {
  package parser;
}
 
program: 
  stm (';' stm)* -> ^(SeqStm stm*)
;
 
stm: 
  ( ID ':=' exp -> ^(AssignStm ID exp)
  | 'print' '(' explist ')' -> ^(PrintStm explist)
  )
;
 
exp:
    e1=multexp ('+' e2=multexp -> ^(OpExp $e1 ^(Plus) $e2)
               |'-' e2=multexp -> ^(OpExp $e1 ^(Minus) $e2)
               |               -> $e1)
;
 
multexp:
    e1=atom ('*' e2=atom -> ^(OpExp $e1 ^(Times) $e2)
            |'/' e2=atom -> ^(OpExp $e1 ^(Div) $e2)
            |            -> $e1)
;
 
atom:
  ( INT -> ^(NumExp INT)
  | ID -> ^(IdExp ID)
  | '(' stm ',' exp ')' -> ^(SeqExp stm exp)
  )
;
 
explist:
  exp (',' exp)* -> ^(ExpList exp*)
;
 
INT : ('0'..'9')('0'..'9')*;
ID : ('a'..'z'|'A'..'Z')+;
WS : (' ' | '\t' | ( '\r\n' | '\n' | '\r')) {$channel=HIDDEN;} ;
SLCOMMENT : '//' (~('\n'|'\r'))* ('\n'|'\r'('\n')?)?  {$channel=HIDDEN;} ;
MLCOMMENT : '/*' .* '*/' {$channel=HIDDEN;} ;