package prec;

import static prec.PrecedencesLexer.ID;
import static prec.PrecedencesLexer.INT;
import static prec.PrecedencesLexer.LEFTPAR;
import static prec.PrecedencesLexer.RIGHTPAR;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeAdaptor;

import prec.Operators.Operator;

public class ManualParser {

	private Operators ops;
	private Token token;
	private PrecedencesLexer lexer;
	private TreeAdaptor adaptor = new CommonTreeAdaptor();

	private void sigToken(){
		token = lexer.nextToken();
	}
	
	private void expect(int tokType) throws PrecedenceParsingException{
		if(token.getType()==tokType){
			sigToken();
			//logger.debug("expect => "+token.getText());
		}
		else{
			throw new PrecedenceParsingException("Error: "+locateError(token)+
					" Se esperaba el token "+tokType+" y aparece "+token.getText());
		}
	}
	
	private String locateError(Token actual) {
		StringBuilder sbf = new StringBuilder();
		
		sbf.append("[");
		sbf.append(actual.getLine());
		sbf.append(",");
		sbf.append(actual.getCharPositionInLine());
		sbf.append("]");
		
		return sbf.toString();
	}
	
	public ManualParser(PrecedencesLexer lexer, Operators ops){
		this.lexer = lexer;
		this.ops = ops;
	}

	public Tree parse() throws PrecedenceParsingException{
		Tree tree=null;
		sigToken();
		tree=exp(0);
		expect(Token.EOF);
		return tree;
	}
	
	private Tree exp(int p)throws PrecedenceParsingException{
		Tree t = primary();
		Tree t1;
		int q = 0;
		Operator op = ops.searchBinary(token.getType());
		Token tok = token;
		while(op!=null && op.precedence >= p){
			sigToken();
			switch (op.assoc) {
			case LEFT:
				q = 1+op.precedence;
				break;
			case RIGHT:
				q = op.precedence;
				break;
			default:
				break;
			}
			t1 = exp(q);
			t = mkBinaryNode(tok, t, t1);
			op = ops.searchBinary(token.getType());
			tok = token;
		}
		return t;
	}

	private Tree primary() throws PrecedenceParsingException{
		Tree tree = null;
		Token tok = token;
		Operator op = ops.searchUnary(token.getType());
		if (op!=null) {// op is unary
			sigToken();
			tree = exp(op.precedence);
			return mkUnaryNode(tok,tree);
		} 
		else if (token.getType()==LEFTPAR){
			sigToken();
			tree = exp(0);
			expect(RIGHTPAR);
			return tree;
		}
		else if(isLeaf(token.getType())){
			tree = mkLeaf(token);
			sigToken();
			return tree;
		}
		else throw new PrecedenceParsingException(locateError(token));
	}

	private Tree mkUnaryNode(Token payload, Tree tree) {
		Tree t = (CommonTree)adaptor.create(payload);
		t.addChild(tree);
		return t;
	}
	
	private Tree mkBinaryNode(Token payload, Tree t1, Tree t2) {
		Tree t = (CommonTree)adaptor.create(payload);
		t.addChild(t1);
		t.addChild(t2);
		return t;
	}
	
	private Tree mkLeaf(Token payload) {
		return (CommonTree)adaptor.create(payload);
	}
	
	private boolean isLeaf(int code){
		return code==ID || code == INT;
	}
	
}
