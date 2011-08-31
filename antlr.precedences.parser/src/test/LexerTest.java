package test;

import java.io.FileInputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.Token;

import prec.PrecedencesLexer;

public class LexerTest {

	public static void main(String[] args) {
		try {
			if (args.length <= 0) {
				System.out.println("usage: java LexerTest <filename>");
			} else {
				// Initialize parser
				PrecedencesLexer lexer = new PrecedencesLexer(new ANTLRInputStream(
						new FileInputStream(args[0])));
				Token tok = lexer.nextToken();
				while (tok.getType()!=PrecedencesLexer.EOF) {
					System.out.println(tok.getText()+">>"+tok.getType());
					tok = lexer.nextToken();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
