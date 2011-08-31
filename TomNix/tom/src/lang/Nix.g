grammar Nix;

options {
  output=AST;
  ASTLabelType=Tree;
}

tokens{
	FORALL='\\forall'; // Cuantificador  Universal
	EXISTS= '\\exists'; // Existencial
	SUM= '\\sum'; // Sumatoria
	UNIONGEN= '\\bigcup'; // Unión generalizada
	INTERSECGEN= '\\bigcap'; // Intersección generalizada
	CARDINAL= '\\sharp'; // Cardinal
	COMPLEMENT= '\\sim'; // Complemento
	NEG= '\\neg'; // Negación lógica
	POWERSET= '\\mathbb{P}'; // Powerset
	TRANSP= '\\top'; // transposición??
	LOR= '\\lor'; // Disyunción
	LAND= '\\land'; // Conjunción
	IMPLIES= '\\Rightarrow'; // Implicación
	DISCREP= '\\not\\equiv'; // Discrepancia
	EQUIV= '\\equiv'; // Equivalencia
	CONSEQ= '\\Leftarrow'; // Consecuencia
	NONASSOCEQ= '\\approx'; // Equivalencia no asociativa
	NEQ= '\\neq'; // Diferente de
	LT= '<'; // Menor que
	LEQ= '\\leq'; // Menor o igual
	GEQ= '\\geq'; // Mayor o igual
	DIVISOR = '\\cdot|'; // Divisor de
	MULTIPLO= '|\\cdot'; // Multiplo de
	DOTTIMES = '\\cdotp'; // Producto
	STAR= '\\ast'; // Producto
	POWER= '\\textasciicircum'; // Potencia
	MAX= '\\uparrow'; // Maximo
	MIN= '\\downarrow'; // Minimo
	MCD= '\\Uparrow'; // Max comun div
	MCM= '\\Downarrow'; // Min comun mul
	UNION= '\\cup'; // Unión
	INTERSEC= '\\cap'; // Intersección
	COINCID= '\\diamond'; // Coincidencia
	PROPERSUBSET= '\\subsetneq'; // Subconjunto propio
	SUBSET= '\\subset'; // Subconjunto
	SUPSET= '\\supset'; // Superconjunto
	PROPERSUPSET= '\\supsetneq'; // Superconjunto propio
	IN= '\\in'; // Pertenece
	NOTIN= '\\notin'; // No pertenece
	CRUZ = '\\times'; // Prod cartesiano
	GENERICSUM= '\\oplus'; // Suma generica
	GENERICPROD= '\\otimes'; // Prod generico
	COMPOSE= '\\circ'; // Composición
	LANGLE= '\\langle'; // Paréntesis angular izquierdo
	RANGLE= '\\rangle'; // Paréntesis angular derecho
	VOIDSET= '\\varnothing'; // Conj vacio
	REAL= '\\mathbb{R}'; // Reales
	NAT= '\\mathbb{N}'; // Naturales
	ENT= '\\mathbb{Z}'; // Enteros
	FUNCDECL= '\\rightarrow'; // Declaracion de funciones
	RELDECL= '\\leftrightarrow'; // Declaracion de relaciones
	ORDEREDPAIR= '\\mapsto'; // Pareja ordenada
	PARCIALFUNC= '\\rightsquigarrow'; // Funcion parcial
	INJECT= '\\rightarrowtail'; // Inyeccion
	PREC= '\\prec'; // Precede (angular), ordenes abstractos estrictos
	PRECEQ= '\\preceq'; // Precede o es igual (angular), ordenes abstractos estrictos
	ABSTRPREC= '\\sqsubset'; // Precede (cuadrado), ordenes abstractos estrictos
	ABSTRPRECEQ= '\\sqsubseteq'; // Precede o es igual (cuadrado), ordenes abstractos estrictos
	SUCC= '\\succ'; // Sucede (angular), ordenes abstractos estrictos
	SUCCEQ= '\\succeq'; // Sucede o es igual (angular), ordenes abstractos estrictos
	ABSTRSUCC= '\\sqsupset'; // Sucede (cuadrado), ordenes abstractos estrictos
	ABSTRSUCCEQ= '\\sqsupseteq'; // Sucede o es igual (cuadrado), ordenes abstractos estrictos
	SUPREM= '\\sqcup'; // Supremo
	INFIM= '\\sqcap'; // Infimo
	EQ= '='; // Igualdad
	CONS= ';'; // Cons
	ADD= '+'; // Suma
	GT= '>'; // Mayor que
}

@header {
  package lang;
}

@lexer::header {
  package lang;
}

prog: ID INT ;


ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :	'0'..'9'+
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
