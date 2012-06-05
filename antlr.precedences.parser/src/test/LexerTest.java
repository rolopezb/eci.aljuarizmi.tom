package test;

import java.io.FileInputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.Token;

import prec.PrecedencesLexer;
import prec.PrecedencesParser;
import eci.antlr.tools.TokenUtil;

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
				TokenUtil tkm = new TokenUtil(PrecedencesParser.tokenNames);
				while (tok.getType()!=Token.EOF) {
					System.out.println(tkm.tokDescription(tok));
					tok = lexer.nextToken();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
