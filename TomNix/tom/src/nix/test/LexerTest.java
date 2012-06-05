package nix.test;

import nix.NixLexer;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRInputStream;

import antlr.Token;

public class LexerTest {
	 	 
	  public static void main(String[] args) {
	    try {
	      if(args.length<=0) {
	        System.out.println("usage: java LexerTest <filename>"); 
	      } else {
	        // Initialize parser
	        NixLexer lexer = new NixLexer(new ANTLRInputStream(new FileInputStream(args[0])));
	        Token t;
	        while ((t=lexer.nextToken().getType())!=Token.EOF) {
				System.out.println(t.getText()+">>"+t.getType());
				
			}
	      }
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
}