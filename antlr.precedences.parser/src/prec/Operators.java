package prec;

import static prec.PrecedencesLexer.*;

public class Operators {
	
	static class Operator {
		int code, precedence;
		Assoc assoc;
		Operator(int code, int prec, Assoc ass){
			this.code=code;
			precedence = prec;
			assoc = ass;
		}
		
		public boolean isLeftAssoc(){
			return assoc==Assoc.LEFT;
		}
		public boolean isRightAssoc(){
			return assoc==Assoc.RIGHT;
		}
		
	}
	enum Assoc {LEFT,RIGHT,NON};
	
	private Operator [] unary = {
		new Operator(MINUS,4,Assoc.NON)
	};
	
	private Operator [] binary = {
		new Operator (LOR,0,Assoc.LEFT),
		new Operator (LAND,1,Assoc.LEFT),
		new Operator (EQ,2,Assoc.LEFT),
		new Operator (ADD,3,Assoc.LEFT),
		new Operator (MINUS,3,Assoc.LEFT),
		new Operator (POR,5,Assoc.LEFT), // es *
		new Operator (DIV,5,Assoc.LEFT),
		new Operator (POWER,6,Assoc.RIGHT)
	};
	
	private Operator search(int code, Operator [] table){
		
		for (int k = 0; k<table.length; k++) {
			if (code==table[k].code) {
				return table[k];
			}
		}
		
		return null;
	}
	
	public Operator searchBinary(int code){
		return search(code, binary);
	}
	
	public Operator searchUnary(int code){
		return search(code, unary);
	}
	
}
