package test;

import java.io.FileInputStream;

import org.antlr.runtime.ANTLRInputStream;

import prec.ManualParser;
import prec.Operators;
import prec.PrecedenceParsingException;
import prec.PrecedencesLexer;

public class ManualParserTest {

	public static void main(String[] args) {
		try {
			if (args.length <= 0) {
				System.out.println("usage: java LexerTest <filename>");
			} else {
				// Initialize parser
				PrecedencesLexer lexer = new PrecedencesLexer(new ANTLRInputStream(
						new FileInputStream(args[0])));
				
				ManualParser parser = new ManualParser(lexer, new Operators());
				parser.parse();
			}
		} catch (PrecedenceParsingException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
